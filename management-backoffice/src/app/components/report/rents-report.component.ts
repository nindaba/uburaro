import {Component} from '@angular/core';
import ReportConfig from '../../../assets/content-config/report-page.json'
import {map, merge, mergeMap, Observable} from "rxjs";
import {Page, Pageable, RentContract, RentOrder} from "../../model/navigation.model";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {ReportService} from "./report.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
    selector: 'mb-rents-report',
    templateUrl: './rents-report.component.html'
})
export class RentsReportComponent{
    orderPageable: FormGroup = new FormGroup({
        currentPage: new FormControl(0),
        pageSize: new FormControl(8),
        sort: new FormControl("orderNumber"),
        sortOrder: new FormControl("desc")
    })
    contractPageable: FormGroup = new FormGroup({
        currentPage: new FormControl(0),
        pageSize: new FormControl(8),
        sort: new FormControl("code"),
        sortOrder: new FormControl("desc")
    })

    contractHead: string[] = ReportConfig.rents.contract;
    orderHead: string[] = ReportConfig.rents.order;
    $contractsWithDateRange: Observable<Page<RentContract>> = merge(this.topService.dateRangeFrom.valueChanges,this.contractPageable.valueChanges).pipe(
        mergeMap( ()=> this.reportService.getContracts(this.contractPageable.getRawValue())));
    $contracts: Observable<Page<RentContract>> = this.reportService.getContracts(this.contractPageable.getRawValue());
    $ordersWithDateRangePage: Observable<Page<RentOrder>> =  merge(this.topService.dateRangeFrom.valueChanges,this.orderPageable.valueChanges).pipe(
        mergeMap( ()=> this.reportService.getFacilityRentOrdersByRange(this.orderPageable.getRawValue())));
    $ordersPage: Observable<Page<RentOrder>> = this.reportService.getFacilityRentOrdersByRange(this.orderPageable.getRawValue());
    constructor(protected reportService:ReportService,protected topService: TopNavService) {
    }
}
