import NavigationConfig from "../../../../assets/content-config/navigation.json";
import {EventEmitter, Injectable} from "@angular/core";
import {BehaviorSubject, Observable, of, Subject, throwError} from "rxjs";
import {FormControl, FormGroup} from "@angular/forms";
import {EndpointConfig, NavNode, NotificationStatus} from "../../../model/navigation.model";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {BreadcrumbsService} from "./breadcrumbs.service";
import {UrlBuilderService} from "../../../utils/UrlBuilder.service";
import {NotificationService} from "../../notification/notification.service";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {NotificationKeys} from "../../../config/notifications.config";

@Injectable({providedIn: "root"})
export class TopNavService {
    $delete: EventEmitter<void> = new EventEmitter<void>();
    nodes: NavNode[] = NavigationConfig["top-nav"].nodes;

    activeNode: NavNode = {};
    itemForm: FormGroup | undefined;
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
        if (this.selectedCodes.includes(this.breadService.facility)) {
            this.breadService.setFacility(this.breadService.CHOSE_FACILITY);
        }
        this.selectedCodes = [];
        this.$delete.emit();
    }

    saveForm(): Observable<any> {
        if (this.itemForm?.invalid){
            return throwError(()=> new Error(NotificationKeys.INVALID_FORM));
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
}