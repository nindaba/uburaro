import {Component, OnDestroy, OnInit} from '@angular/core';
import {NavNode} from "../../model/navigation.model";
import NavigationConfig from "../../../assets/content-config/navigation.json";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {InvoiceService} from "./invoice.service";
import {Subject} from "rxjs";

@Component({
    selector: 'mb-landing-page',
    templateUrl: './landing-page.component.html'
})
export class LandingPageComponent implements OnInit,OnDestroy{
    tabs: NavNode[] = NavigationConfig.clientDetailsTabs.nodes;
    clientCode: string ="";
    ordersInInvoice: Subject<number> = this.invoiceService.getOrderCount();
    constructor(protected breadService: BreadcrumbsService,protected invoiceService: InvoiceService) {
    }

    ngOnInit(): void {
        this.clientCode =  this.breadService.pages.details || "";
    }

    ngOnDestroy(): void {
        this.invoiceService.resetOrders();
    }

}

