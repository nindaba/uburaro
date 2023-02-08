import {Injectable} from "@angular/core";
import {endpointsConfig} from "../config/endpoints.config";
import {EndpointConfig} from "../model/navigation.model";
import {KeyValue} from "@angular/common";

@Injectable({
    providedIn: "root"
})
export class UrlBuilderService {
    getUrl(name: string, variables: KeyValue<string, any>[] = [], params: KeyValue<string, any>[] = []): string {
        let url = endpointsConfig["baseUrl"] + endpointsConfig[name];
        variables.forEach(value => url = url.replace(`{${value.key}}`, value.value))

        if (params.length > 0) {
            let paramsCombined = params.map(value => `${value.key}=${value.value}`).join("&");
            url = url.concat("?").concat(paramsCombined)
        }
        return url;
    }
}