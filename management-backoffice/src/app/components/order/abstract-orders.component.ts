import {Component, Injectable, Input, OnDestroy, OnInit} from "@angular/core";
import {Order} from "../../model/navigation.model";
import {InvoiceService} from "../client/invoice.service";
import {OrderService} from "../inventory/order.service";
import {mergeMap, Observable, of, tap} from "rxjs";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";

@Injectable()
export abstract class AbstractOrdersComponent<ORDER extends Order> implements OnInit, OnDestroy {
    protected selected: Order[] = [];
    $orders: Observable<ORDER[]> = new Observable();
    $refreshedOrders: Observable<ORDER[]> = this.topService.$refreshData.pipe(mergeMap(()=> this.refreshOrders()));
    constructor(protected invoiceService: InvoiceService,
                protected orderService: OrderService,
                protected topService: TopNavService,
                protected breadService: BreadcrumbsService) {
    }

    addOrder(order: Order) {
        let number = this.selected.map(value => value.orderNumber).indexOf(order.orderNumber);
        if (number > -1) {
            this.selected.splice(number, 1);
            this.invoiceService.addOrderCount(-1);
        } else {
            this.selected.push(order);
            this.invoiceService.addOrderCount(1);
        }
    }

    isSelected(order: Order): boolean {
        return this.selected.map(value => value.orderNumber).indexOf(order.orderNumber) >= 0;
    }

    ngOnDestroy(): void {
        this.invoiceService.orders = this.selected;
        this.invoiceService.resetSelection();
    }

    ngOnInit(): void {
        this.$orders = this.getOrders();
        this.selected = this.invoiceService.orders;
    }

    abstract getOrders(): Observable<ORDER[]>

    private refreshOrders() : Observable<ORDER[]>{
        let orders = this.selected.length ? this.$orders : of([])
        this.invoiceService.resetOrders()
        this.selected.splice(0);
        return orders;
    }
}