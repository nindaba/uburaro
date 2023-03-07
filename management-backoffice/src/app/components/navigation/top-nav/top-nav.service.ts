import NavigationConfig from "../../../../assets/content-config/navigation.json";
import {EventEmitter, Injectable} from "@angular/core";
import {BehaviorSubject, Subject} from "rxjs";
import {FormControl} from "@angular/forms";
import {EndpointConfig, NavNode, NotificationStatus} from "../../../model/navigation.model";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {BreadcrumbsService} from "./breadcrumbs.service";
import {UrlBuilderService} from "../../../utils/UrlBuilder.service";
import {NotificationService} from "../../notification/notification.service";

@Injectable({providedIn: "root"})
export class TopNavService {
    $delete: EventEmitter<void> = new EventEmitter<void>();
    nodes: NavNode[] = NavigationConfig["top-nav"].nodes;

    activeNode: NavNode = {};
    formValues: any;
    selectedCodes: string[] = [];
    $formChanged: Subject<boolean> = new BehaviorSubject(false);
    searchForm: FormControl = new FormControl('');
    $confirmDelete: Subject<boolean> = new BehaviorSubject(false);

    constructor(
        private router: Router,
        private endpoints: EndpointConfig,
        private http: HttpClient,
        private breadService: BreadcrumbsService,
        private urlBuilder: UrlBuilderService,
        private notification: NotificationService
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
        if (this.selectedCodes.length > 0 || this.breadService.pages.details) {
            this.selectedCodes.push(this.breadService.pages.details || "");

            this.http.delete(this.urlBuilder.getBaseUrlForPage(), {params: {"codes": this.selectedCodes.join(",")}}
            ).subscribe({
                next: value => this.deleteSuccess(),
                error: (err: HttpErrorResponse) => this.notification.notify(err.message, NotificationStatus.NONE)
            })
        }
    }


    private deleteSuccess() {
        this.notification.notify("notification.delete.completed", NotificationStatus.SUCCESS)
        this.$delete.emit();
        if (this.selectedCodes.includes(this.breadService.facility)) {
            this.breadService.setFacility(this.breadService.CHOSE_FACILITY);
        }
        this.selectedCodes = [];
        this.router.navigate([this.breadService.pages.page])
    }

    saveForm() {
        this.http.patch(this.urlBuilder.getBaseUrlForPage(), this.formValues).subscribe({
            next: value => this.patched([this.breadService.pages.page,this.formValues.code]),
            error: (err: HttpErrorResponse) => this.notification.notify(err.message, NotificationStatus.NONE)
        })
    }

    private createItem() {
        this.http.post(this.urlBuilder.getFullUrl(), this.formValues).subscribe({
            next: value => this.notification.notify("notification.creation.completed", NotificationStatus.SUCCESS)
        })

    }

    private updateItem() {
        this.http.patch(this.urlBuilder.getFullUrl(), this.formValues).subscribe({
            next: value => this.notification.notify("notification.creation.completed", NotificationStatus.SUCCESS)
        })
    }

    private patched(redirect:string[]) {
        this.notification.notify("notification.save.completed", NotificationStatus.SUCCESS);
        this.router.navigate([this.breadService.pages.page])
            .then(() =>this.router.navigate(redirect));

        this.$formChanged.next(false);
        this.formValues = {}
    }
}