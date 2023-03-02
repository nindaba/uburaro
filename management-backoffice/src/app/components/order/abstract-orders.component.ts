import {Component, Injectable, Input, OnDestroy, OnInit} from "@angular/core";
import {Order} from "../../model/navigation.model";
import {InvoiceService} from "../client/invoice.service";

@Injectable()
export abstract class AbstractOrdersComponent implements OnInit, OnDestroy{
    protected selected: Order[] = [];


    constructor(protected invoiceService: InvoiceService) {
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
    }

    ngOnInit(): void {
        this.selected = this.invoiceService.orders;
    }
}