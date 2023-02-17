import {Injectable} from "@angular/core";
import {KeyValue} from "@angular/common";
import {BreadcrumbsService} from "../components/navigation/top-nav/breadcrumbs.service";
import {EndpointConfig} from "../model/navigation.model";
import {NEW_ITEM} from "../components/navigation/navigation.constants";

@Injectable({
    providedIn: "root"
})
export class UrlBuilderService {
    constructor(private bread: BreadcrumbsService, private config: EndpointConfig) {
    }

    getUrl(name: string, variables: KeyValue<string, any>[] = [], params: KeyValue<string, any>[] = []): string {
        let url = this.config.baseUrl + this.config[name];
        variables.forEach(value => url = url.replace(`{${value.key}}`, value.value))

        if (params.length > 0) {
            let paramsCombined = params.map(value => `${value.key}=${value.value}`).join("&");
            url = url.concat("?").concat(paramsCombined)
        }
        return url;
    }

    getFullUrl(allFields: boolean = false): string {
        let pages = this.bread.pages;
        return this.getUrl(pages.details && pages.details != NEW_ITEM ? this.config.relation[pages.page || 'def'] : pages.page || 'def',
            [
                {
                    key: "code",
                    value: this.bread.facility == this.bread.CHOSE_FACILITY ? this.bread.pages.details : this.bread.facility
                },
                {
                    key: `${this.config.relation[pages.page || 'def']}-code`,
                    value: pages.details
                }
            ],
            [
                {
                    key: "allFields",
                    value: allFields
                }
            ]);
    }
    getBaseUrlForPage(){
        return this.getUrl(this.bread.pages.page || "").replace("{code}",this.bread.facility);
    }
}