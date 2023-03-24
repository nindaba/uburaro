import {ReportService} from "../components/report/report.service";
import {Injectable} from "@angular/core";
import {UrlBuilderService} from "../utils/UrlBuilder.service";
import {ClientReport, DateRange, Page, Pageable, RentContract, RentOrder} from "../model/navigation.model";
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

    getContracts(pageable:Pageable): Observable<Page<RentContract>> {
        let url = this.urlBuilder.getUrlForEndPoint("contracts");
        let rangeFrom = this.topService.dateRangeFrom;

        if(rangeFrom.valid) {
            return this.http.post<Page<RentContract>>(url,rangeFrom.getRawValue(),{params: {...pageable}});
        }
        return new Observable();
    }

    getFacilityRentOrdersByRange(pageable:Pageable): Observable<Page<RentOrder>> {
        let url = this.urlBuilder.getUrlForEndPoint("rentFacilityOrders");
        let rangeFrom = this.topService.dateRangeFrom;

        if(rangeFrom.valid) {
            return this.http.post<Page<RentOrder>>(url,rangeFrom.getRawValue(),{params: {...pageable}});
        }
        return new Observable();
    }
}
