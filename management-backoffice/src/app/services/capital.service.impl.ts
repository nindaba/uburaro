import {Injectable} from "@angular/core";
import {CapitalService} from "../components/facility/capital.service";
import {Capital, CapitalEntry, CapitalType, DateRange, EndpointConfig, Page, Pageable} from "../model/navigation.model";
import {map, Observable, of} from "rxjs";
import {HttpClient, HttpParams, HttpRequest} from "@angular/common/http";
import {BreadcrumbsService} from "../components/navigation/top-nav/breadcrumbs.service";
import {UrlBuilderService} from "../utils/UrlBuilder.service";
import {TopNavService} from "../components/navigation/top-nav/top-nav.service";

@Injectable()
export class CapitalServiceImpl implements CapitalService {
    constructor(
        private http: HttpClient,
        private bread: BreadcrumbsService,
        private urlBuilder: UrlBuilderService,
        private topService:TopNavService) {
    }

    addCapital(value: number, type: CapitalType,description:string=""): Observable<any> {
        let url = this.urlBuilder.getUrl("capital", [{
            key: "code",
            value: this.bread.pages.details
        }]).concat(`/${value}/${type}/${description}`);
        return this.http.post(url, {});
    }

    getCapitalEntries(dateRange?:DateRange): Observable<CapitalEntry[]> {
        let url = this.urlBuilder.getUrlForEndPoint("capitalEntries");
        let dateRangeFrom = this.topService.dateRangeFrom;
        if(dateRange || dateRangeFrom.valid)
        {
            return this.http.post<CapitalEntry[]>(url, dateRange || dateRangeFrom.getRawValue());
        }
        return new Observable();
    }

    getCapitalEntriesPage(pageable?: Pageable,dateRange?:DateRange): Observable<Page<CapitalEntry>> {
        let url = this.urlBuilder.getUrlForEndPoint("capitalEntries");
        let dateRangeFrom = this.topService.dateRangeFrom;
        if(dateRange || dateRangeFrom.valid)
        {
            return this.http.post<Page<CapitalEntry>>(url, dateRange || dateRangeFrom.getRawValue(),{params: {...pageable}});
        }
        return new Observable();
    }

    getCapital(): Observable<Capital> {
        let url = this.urlBuilder.getUrlForEndPoint("capital");
        return this.http.get<Capital>(url);
    }

}