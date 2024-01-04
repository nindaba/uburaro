import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {mergeMap, Observable, of} from "rxjs";
import {InventoryOrder, Order} from "../../model/navigation.model";
import DetailsConfig from "../../../assets/content-config/details-page.json";
import {InvoiceService} from "../client/invoice.service";
import {AbstractOrdersComponent} from "./abstract-orders.component";
import {CLIENTS_ROUTE, INVENTORIES_ROUTE} from "../navigation/navigation.constants";

@Component({
    selector: 'mb-inventory-orders',
    templateUrl: './inventory-orders.component.html'
})
export class InventoryOrdersComponent extends AbstractOrdersComponent<InventoryOrder>{
    @Input()
    clientPage: boolean = false;
    orderHeads: string[] = DetailsConfig.inventory.order.heads;
    $withCreatedOrder: Observable<InventoryOrder[]> = this.topService.$createdItem.pipe(mergeMap(()=> this.$orders));


    getOrders(): Observable<InventoryOrder[]> {
        if(this.breadService.pages.page == CLIENTS_ROUTE){
            return this.orderService.getOrdersByClientCode<InventoryOrder[]>();
        }
        if(this.breadService.pages.page == INVENTORIES_ROUTE){
            return this.orderService.getOrdersByInventoryCode<InventoryOrder[]>();
        }
        return new Observable();
    }
}
