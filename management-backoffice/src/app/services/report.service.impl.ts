import {ReportService} from "../components/report/report.service";
import {Injectable} from "@angular/core";
import {UrlBuilderService} from "../utils/UrlBuilder.service";
import {ClientReport, DateRange} from "../model/navigation.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {TopNavService} from "../components/navigation/top-nav/top-nav.service";

@Injectable()
export class ReportServiceImpl implements ReportService {

    constructor(protected urlBuilder: UrlBuilderService, protected http: HttpClient, protected topService: TopNavService) {
    }

    getClientReport(range?: DateRange): Observable<ClientReport> {
        return this.http.post<ClientReport>(this.urlBuilder.getUrlForEndPoint("clientsReport"), range || this.topService.dateRangeFrom.getRawValue());
    }

}