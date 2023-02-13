import NavigationConfig from "../../../../assets/content-config/navigation.json";
import {EventEmitter, Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";
import {FormControl} from "@angular/forms";
import {EndpointConfig, NavNode} from "../../../model/navigation.model";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {BreadcrumbsService} from "./breadcrumbs.service";
import {NEW_ITEM} from "../navigation.constants";
import {UrlBuilderService} from "../../../utils/UrlBuilder.service";

@Injectable({providedIn: "root"})
export class TopNavService {
    $delete: EventEmitter<void> = new EventEmitter<void>();
    nodes: NavNode[] = NavigationConfig["top-nav"].nodes;

    activeNode: NavNode = {};
    formValues: any;
    selectedCodes: string[] = [];
    $formChanged: BehaviorSubject<boolean> = new BehaviorSubject(false);
    searchForm: FormControl = new FormControl('');

    constructor(
        private router: Router,
        private endpoints: EndpointConfig,
        private http: HttpClient,
        private breadService: BreadcrumbsService,
        private urlBuilder: UrlBuilderService
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
        if (this.selectedCodes.length > 0) {
            this.http.delete(
                this.urlBuilder.getFullUrl(),
                {params: {"codes": this.selectedCodes.join(",")}}
            ).subscribe({
                next: value => this.$delete.emit()
            })
        }
    }


    saveForm() {
        if (this.breadService.pages.details == NEW_ITEM) {
            this.createItem();
        } else {
            this.updateItem();
        }
    }

    private createItem() {
        this.http.post(this.urlBuilder.getFullUrl(), this.formValues).subscribe({next: value => {}})

    }

    private updateItem() {
        this.http.patch(this.urlBuilder.getFullUrl(), this.formValues).subscribe({
            next: value => {
            }
        })
    }

}