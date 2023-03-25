import {Component} from '@angular/core';
import {merge, mergeMap, Observable} from "rxjs";
import {InventoryOrder, InventoryOrderType, Page, Pageable} from "../../model/navigation.model";
import {ReportService} from "./report.service";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import ReportConfig from "../../../assets/content-config/report-page.json"
import {FormControl, FormGroup} from "@angular/forms";

@Component({
    selector: 'mb-inventories-report',
    templateUrl: './inventories-report.component.html'
})
export class InventoriesReportComponent {
    inPage : FormGroup = new FormGroup({
        currentPage: new FormControl(0),
        pageSize: new FormControl(8),
        sort: new FormControl("orderNumber"),
        sortOrder: new FormControl("desc")
    });

    outPage : FormGroup = new FormGroup({
        currentPage: new FormControl(0),
        pageSize: new FormControl(8),
        sort: new FormControl("orderNumber"),
        sortOrder: new FormControl("desc")
    })

    soldPage : FormGroup = new FormGroup({
        currentPage: new FormControl(0),
        pageSize: new FormControl(8),
        sort: new FormControl("orderNumber"),
        sortOrder: new FormControl("desc")
    })

    $inOrdersOnChange: Observable<Page<InventoryOrder>> = this.getInOrdersOnChange(this.inPage.getRawValue())
    $inOrders: Observable<Page<InventoryOrder>> = this.reportService.getFacilityInventoryOrdersByRange(this.inPage.getRawValue(),InventoryOrderType.REFILL);
    $outOrdersOnChange: Observable<Page<InventoryOrder>> = this.getOutOrdersOnChange(this.outPage.getRawValue())
    $outOrders: Observable<Page<InventoryOrder>> = this.reportService.getFacilityInventoryOrdersByRange(this.outPage.getRawValue(),InventoryOrderType.OUT);
    $soldOrdersOnChange: Observable<Page<InventoryOrder>> = this.getSoldOrdersOnChange(this.soldPage.getRawValue());
    $soldOrders: Observable<Page<InventoryOrder>> = this.reportService.getFacilityInventoryOrdersByRange(this.soldPage.getRawValue(),InventoryOrderType.SOLD);
    inOrderHead: string[] = ReportConfig.inventories.inOrders;
    outOrderHead: string[] = ReportConfig.inventories.outOrders;
    soldOrderHead: string[] = ReportConfig.inventories.soldOrders;

    constructor(protected reportService: ReportService, protected topService: TopNavService) {
    }

    private getInOrdersOnChange(pageable: Pageable): Observable<Page<InventoryOrder>> {
        return merge(this.inPage.valueChanges,this.topService.dateRangeFrom.valueChanges).pipe(
            mergeMap(() => this.reportService.getFacilityInventoryOrdersByRange(pageable,InventoryOrderType.REFILL)))
    }
    private getOutOrdersOnChange(pageable: Pageable): Observable<Page<InventoryOrder>> {
        return merge(this.outPage.valueChanges,this.topService.dateRangeFrom.valueChanges).pipe(
            mergeMap(() => this.reportService.getFacilityInventoryOrdersByRange(pageable,InventoryOrderType.OUT)))
    }
    private getSoldOrdersOnChange(pageable: Pageable): Observable<Page<InventoryOrder>> {
        return merge(this.soldPage.valueChanges,this.topService.dateRangeFrom.valueChanges).pipe(
            mergeMap(() => this.reportService.getFacilityInventoryOrdersByRange(pageable,InventoryOrderType.SOLD)))
    }
}
