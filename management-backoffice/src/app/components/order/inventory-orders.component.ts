import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Observable, of} from "rxjs";
import {InventoryOrder, Order} from "../../model/navigation.model";
import DetailsConfig from "../../../assets/content-config/details-page.json";
import {InvoiceService} from "../client/invoice.service";
import {AbstractOrdersComponent} from "./abstract-orders.component";

@Component({
    selector: 'mb-inventory-orders',
    templateUrl: './inventory-orders.component.html'
})
export class InventoryOrdersComponent extends AbstractOrdersComponent{

    @Input()
    clientPage: boolean = false;

    @Input("orders")
    $inventoryOrders: Observable<InventoryOrder[]> = new Observable();

    orderHeads: string[] = DetailsConfig.inventory.order.heads;

}