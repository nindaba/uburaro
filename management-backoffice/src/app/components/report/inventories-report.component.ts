import {Component} from '@angular/core';
import {mergeMap, Observable} from "rxjs";
import {InventoryOrder, InventoryOrderType, Page, Pageable} from "../../model/navigation.model";
import {ReportService} from "./report.service";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import ReportConfig from "../../../assets/content-config/report-page.json"

@Component({
    selector: 'mb-inventories-report',
    templateUrl: './inventories-report.component.html'
})
export class InventoriesReportComponent {

    inPage: Pageable = {currentPage: 0, pageSize: 10, sort: "orderNumber", sortOrder: "desc"};
    outPage: Pageable = {currentPage: 0, pageSize: 10, sort: "orderNumber", sortOrder: "desc"};
    soldPage: Pageable = {currentPage: 0, pageSize: 10, sort: "orderNumber", sortOrder: "desc"};
    $inOrdersOnChange: Observable<Page<InventoryOrder>> = this.getOrdersOnChange(InventoryOrderType.REFILL, this.inPage)
    $inOrders: Observable<Page<InventoryOrder>> = this.reportService.getFacilityInventoryOrdersByRange(this.inPage,InventoryOrderType.REFILL);
    $outOrdersOnChange: Observable<Page<InventoryOrder>> = this.getOrdersOnChange(InventoryOrderType.OUT, this.outPage)
    $outOrders: Observable<Page<InventoryOrder>> = this.reportService.getFacilityInventoryOrdersByRange(this.outPage,InventoryOrderType.OUT);
    $soldOrdersOnChange: Observable<Page<InventoryOrder>> = this.getOrdersOnChange(InventoryOrderType.SOLD,this.soldPage);
    $soldOrders: Observable<Page<InventoryOrder>> = this.reportService.getFacilityInventoryOrdersByRange(this.soldPage,InventoryOrderType.SOLD);
    inOrderHead: string[] = ReportConfig.inventories.inOrders;
    outOrderHead: string[] = ReportConfig.inventories.outOrders;
    soldOrderHead: string[] = ReportConfig.inventories.soldOrders;

    constructor(protected reportService: ReportService, protected topService: TopNavService) {
    }

    private getOrdersOnChange(orderType: InventoryOrderType,pageable: Pageable): Observable<Page<InventoryOrder>> {
        return this.topService.dateRangeFrom.valueChanges.pipe(
            mergeMap(() => this.reportService.getFacilityInventoryOrdersByRange(pageable,orderType)))
    }
}
