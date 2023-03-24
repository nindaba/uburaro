import {Component} from '@angular/core';
import ReportConfig from '../../../assets/content-config/report-page.json'
import {map, mergeMap, Observable} from "rxjs";
import {Page, Pageable, RentContract, RentOrder} from "../../model/navigation.model";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {ReportService} from "./report.service";

@Component({
    selector: 'mb-rents-report',
    templateUrl: './rents-report.component.html'
})
export class RentsReportComponent{
    orderPageable:Pageable ={
        currentPage: 0, pageSize: 10, sort: "orderNumber", sortOrder: "desc"
    }
    contractPageable:Pageable ={
        currentPage: 0, pageSize: 10, sort: "code", sortOrder: "desc"
    }

    contractHead: string[] = ReportConfig.rents.contract;
    orderHead: string[] = ReportConfig.rents.order;
    $contractsWithDateRange: Observable<Page<RentContract>> = this.topService.dateRangeFrom.valueChanges.pipe(
        mergeMap( ()=> this.reportService.getContracts(this.contractPageable)));
    $contracts: Observable<Page<RentContract>> = this.reportService.getContracts(this.contractPageable);
    $ordersWithDateRangePage: Observable<Page<RentOrder>> =  this.topService.dateRangeFrom.valueChanges.pipe(
        mergeMap( ()=> this.reportService.getFacilityRentOrdersByRange(this.orderPageable)));
    $ordersPage: Observable<Page<RentOrder>> = this.reportService.getFacilityRentOrdersByRange(this.orderPageable);
    constructor(protected reportService:ReportService,protected topService: TopNavService) {
    }

}
