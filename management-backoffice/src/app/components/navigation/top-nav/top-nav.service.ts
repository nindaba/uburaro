import NavigationConfig from "../../../../assets/content-config/navigation.json";
import {EventEmitter, Injectable} from "@angular/core";
import {BehaviorSubject, filter, Observable, Subject, tap, throwError} from "rxjs";
import {FormControl, FormGroup} from "@angular/forms";
import {DateRange, EndpointConfig, NavNode, NotificationStatus} from "../../../model/navigation.model";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {BreadcrumbsService} from "./breadcrumbs.service";
import {UrlBuilderService} from "../../../utils/UrlBuilder.service";
import {NotificationService} from "../../notification/notification.service";
import {NotificationKeys} from "../../../config/notifications.config";
import {InvoiceService} from "../../client/invoice.service";
import {ContractService} from "../../rent/contract.service";
import {formatDate} from "@angular/common";
import {dateRangeValidator} from "../../../validators/daterange.validator";

@Injectable({providedIn: "root"})
export class TopNavService {
    $refreshData: EventEmitter<void> = new EventEmitter<void>();
    $createdItem: EventEmitter<void> = new EventEmitter<void>();
    nodes: NavNode[] = NavigationConfig["top-nav"].nodes;

    itemForm: FormGroup | undefined;
    selectedCodes: string[] = [];
    $formChanged: Subject<boolean> = new BehaviorSubject(false);
    searchForm: FormControl = new FormControl('');
    $confirmDelete: Subject<boolean> = new BehaviorSubject(false);
    dateRangeFrom: FormGroup = new FormGroup({});

    constructor(
        private router: Router,
        private endpoints: EndpointConfig,
        private http: HttpClient,
        private breadService: BreadcrumbsService,
        private urlBuilder: UrlBuilderService,
        private notification: NotificationService,
        private invoiceService: InvoiceService,
        private contractService: ContractService
    ) {
    }

    getNode(nodeId: string): NavNode {
        return this.nodes.find(node => node.id === nodeId) || {};
    }

    isEnabled(nodeId: string): boolean {
        let path = this.router.url.split("?")[0].split("/")[1];
        return !(nodeId == "discard" || nodeId == "save" || this.getNode(nodeId).disabledOn?.includes(path));
    }

    getName(nodeId: string): string {
        return this.getNode(nodeId).name || nodeId;
    }

    selectionChanged(code: string) {
        let indexOf = this.selectedCodes.indexOf(code);
        if (indexOf > -1) {
            this.selectedCodes.splice(indexOf, 1);
        } else this.selectedCodes.push(code);
    }

    delete() {
        if (this.breadService.pages.details) {
            this.selectedCodes = [this.breadService.pages.details]
        }

        let url = this.getDeleteInvoices()
            || this.getDeleteOrders()
            || this.getDeleteContract()
            || this.urlBuilder.getBaseUrlForPage();

        if (this.selectedCodes.length > 0) {
            this.http.delete(url, {params: {"codes": this.selectedCodes.join(",")}}
            ).subscribe({
                next: () => this.deleteSuccess(),
                error: (err: HttpErrorResponse) => this.notification.notify(err.message, NotificationStatus.NONE)
            })
        }
    }


    private getDeleteInvoices(): string | undefined {
        let invoices = this.invoiceService.getSelectedInvoices();
        if (invoices.length) {
            this.selectedCodes = invoices.map((invoice) => invoice.invoiceNumber || "").filter(invoice => invoice);
            return this.urlBuilder.getUrlForEndPoint("clientInvoices");
        }
        return;
    }

    private getDeleteOrders(): string | undefined {
        let orders = this.invoiceService.orders;
        if (orders.length) {
            this.selectedCodes = orders.map(order => order.orderNumber || "").filter(order => order);
            return this.urlBuilder.getUrlForEndPoint("clientOrders");
        }
        return;
    }

    private deleteSuccess() {
        if (this.invoiceService.orders) {
            this.invoiceService.getOrderCount().next(0)
        }

        if (this.selectedCodes.includes(this.breadService.pages.details || "detailsPage")) {
            this.breadService.pages.details = "";
            this.router.navigate([this.breadService.pages.page])
        }

        this.notification.notify(NotificationKeys.DELETION_COMPLETED, NotificationStatus.SUCCESS)
        if (this.selectedCodes.includes(this.breadService.facility)) {
            this.breadService.setFacility(this.breadService.CHOSE_FACILITY);
        }
        this.selectedCodes.splice(0);
        this.$refreshData.emit();
    }

    saveForm(): Observable<any> {
        if (this.itemForm?.invalid) {
            return throwError(() => new Error(NotificationKeys.INVALID_FORM));
        }
        return this.http.patch(this.urlBuilder.getBaseUrlForPage(), this.itemForm?.getRawValue());
    }

    private createItem() {
        this.http.post(this.urlBuilder.getFullUrl(), this.itemForm?.getRawValue()).subscribe({
            next: value => this.notification.notify(NotificationKeys.CREATION_COMPLETED, NotificationStatus.SUCCESS)
        })

    }

    private updateItem() {
        this.http.patch(this.urlBuilder.getFullUrl(), this.itemForm).subscribe({
            next: value => this.notification.notify(NotificationKeys.CREATION_COMPLETED, NotificationStatus.SUCCESS)
        })
    }

    patched(redirect: string[]) {
        this.notification.notify("notification.save.completed", NotificationStatus.SUCCESS);
        this.router.navigate([this.breadService.pages.page])
            .then(() => this.router.navigate(redirect));

        this.$formChanged.next(false);
        this.itemForm?.reset();
    }

    private getDeleteContract(): string | undefined {
        let contracts = this.contractService.getSelected();
        if (contracts.length) {
            this.selectedCodes = contracts.map((contract) => contract.code || "").filter(invoice => invoice);
            return this.urlBuilder.getUrlForEndPoint("rentContract");
        }
        return;
    }

    public createRangeForm() {
        let from = new Date();
        let formGroup = new FormGroup({
            from: new FormControl(formatDate(from.setMonth(from.getMonth() - 1), "yyyy-MM-dd", "en")),
            to: new FormControl(formatDate(new Date(), "yyyy-MM-dd", "en"))
        },dateRangeValidator);
        this.dateRangeFrom = formGroup;
        return formGroup;
    }

    createDownloadLink(range: DateRange = this.dateRangeFrom.getRawValue()):string {
        const params = `?from=${range.from}&to=${range.to}`;
        const route = this.router.url.split("/").pop() +"Pdf";
        return this.urlBuilder.getUrlForEndPoint(route)+params;
    }
}