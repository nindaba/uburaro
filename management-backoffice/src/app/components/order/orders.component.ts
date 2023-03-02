import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Observable, of} from "rxjs";
import {InventoryOrder, Order} from "../../model/navigation.model";
import DetailsConfig from "../../../assets/content-config/details-page.json";
import {InvoiceService} from "../client/invoice.service";
import {AbstractOrdersComponent} from "./abstract-orders.component";

@Component({
    selector: 'mb-orders',
    templateUrl: './orders.component.html'
})
export class OrdersComponent{

    @Input("orders")
    orders: Order[] = [];

    orderHeads: string[] = DetailsConfig.invoice.order.heads;

}
