import {Injectable} from "@angular/core";
import {CapitalService} from "../components/facility/capital.service";
import {Capital, CapitalEntry, CapitalType, EndpointConfig} from "../model/navigation.model";
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {BreadcrumbsService} from "../components/navigation/top-nav/breadcrumbs.service";
import {UrlBuilderService} from "../utils/UrlBuilder.service";

@Injectable()
export class CapitalServiceImpl implements CapitalService {
    constructor(
        private http: HttpClient,
        private bread: BreadcrumbsService,
        private urlBuilder: UrlBuilderService) {
    }

    addCapital(value: number, type: CapitalType): Observable<any> {
        let url = this.urlBuilder.getUrl("capital", [{
            key: "code",
            value: this.bread.pages.details
        }]).concat(`/${value}/${type}`);
        return this.http.post(url, {});
    }

    getCapitalEntries(from: Date, to: Date): Observable<CapitalEntry[]> {
        let url = this.urlBuilder.getUrl("capitalEntries", [{key: "code", value: this.bread.pages.details}]);
        let params: any = {from: from, to: to}

        return this.http.get<CapitalEntry[]>(url, {params: params});
    }

    getCapital(): Observable<Capital> {
        let url = this.urlBuilder.getUrl("capital", [{
            key: "code",
            value: this.bread.pages.details
        }])
        return this.http.get<Capital>(url);
    }

}