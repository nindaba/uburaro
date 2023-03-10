import {Injectable} from "@angular/core";
import {CapitalService} from "../components/facility/capital.service";
import {Capital, CapitalEntry, CapitalType, DateRange, EndpointConfig} from "../model/navigation.model";
import {map, Observable} from "rxjs";
import {HttpClient, HttpParams, HttpRequest} from "@angular/common/http";
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

    getCapitalEntries(dateRange:DateRange): Observable<CapitalEntry[]> {
        let url = this.urlBuilder.getUrlForEndPoint("capitalEntries");
        return this.http.post<CapitalEntry[]>(url, dateRange);
    }

    getCapital(): Observable<Capital> {
        let url = this.urlBuilder.getUrlForEndPoint("capital");
        return this.http.get<Capital>(url);
    }

}