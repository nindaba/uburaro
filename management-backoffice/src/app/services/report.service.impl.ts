import {ReportService} from "../components/report/report.service";
import {Injectable} from "@angular/core";
import {UrlBuilderService} from "../utils/UrlBuilder.service";
import {
    ClientReport,
    DateRange,
    InventoryOrder, InventoryOrderType, Item,
    Page,
    Pageable,
    RentContract,
    RentOrder
} from "../model/navigation.model";
import {map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {TopNavService} from "../components/navigation/top-nav/top-nav.service";

@Injectable()
export class ReportServiceImpl implements ReportService {

    constructor(protected urlBuilder: UrlBuilderService, protected http: HttpClient, protected topService: TopNavService) {
    }

    getClientReport(pageable: Pageable,range?: DateRange): Observable<ClientReport> {
        return this.http.post<ClientReport>(
            this.urlBuilder.getUrlForEndPoint("clientsReport"),
            range || this.topService.dateRangeFrom.getRawValue(),
            {params: {...pageable}});
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
        return this.getFacilityOrdersByRange(this.urlBuilder.getUrlForEndPoint("rentFacilityOrders"),pageable);
    }

    getFacilityInventoryOrdersByRange(pageable: Pageable,orderEntry:InventoryOrderType): Observable<Page<InventoryOrder>> {
        return this.getFacilityOrdersByRange<InventoryOrder>(
            this.urlBuilder.getUrlForEndPoint("inventoryFacilityOrders"),
            pageable, {orderEntry: orderEntry});
    }
    protected getFacilityOrdersByRange<ITEM extends Item>(url:string,pageable: Pageable,additionalParam?: {[key:string]: any}): Observable<Page<ITEM>> {
        let rangeFrom = this.topService.dateRangeFrom;

        if(rangeFrom.valid) {
            return this.http.post<Page<ITEM>>(url,rangeFrom.getRawValue(),{params: {...pageable,...additionalParam}});
        }
        return new Observable();
    }
}
