import {Injectable} from "@angular/core";
import {KeyValue} from "@angular/common";
import {BreadcrumbsService} from "../components/navigation/top-nav/breadcrumbs.service";
import {EndpointConfig} from "../model/navigation.model";

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

    getFullUrl(): string {
        let pages = this.bread.pages;
        console.log(pages)
        return this.getUrl(pages.details ? this.config.relation[pages.page || 'def'] : pages.page || 'def', [
            {
                key: "code",
                value: this.bread.facility
            },
            {
                key: `${this.config.relation[pages.page || 'def']}-code`,
                value: pages.details
            }
        ])
    }
}