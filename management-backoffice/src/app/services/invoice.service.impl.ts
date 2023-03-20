import {InvoiceService} from "../components/client/invoice.service";
import {Invoice, Order} from "../model/navigation.model";
import {Injectable} from "@angular/core";
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {UrlBuilderService} from "../utils/UrlBuilder.service";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class InvoiceServiceImpl implements InvoiceService {
    protected orders_: Order[] = [];
    protected orderCount: number = 0;
    protected orderLength: Subject<number> = new BehaviorSubject(this.orderCount);
    selectedInvoices: Invoice[] = [];

    constructor(protected urlBuilder: UrlBuilderService, protected http: HttpClient) {
    }

    set orders(orders: Order[]) {
        this.orders_ = orders;
        this.orderLength.next(this.orders.length);
    }

    get orders(): Order[] {
        return this.orders_;
    }

    getOrderCount(): Subject<number> {
        return this.orderLength;
    }

    removeOrder(order: Order): void {
        let number = this.orders.indexOf(order);
        this.orders.splice(number, 1);
        this.addOrderCount(-1);
    }

    addOrderCount(count: number): void {
        this.orderCount += count;
        this.orderLength.next(this.orderCount);
    }

    createInvoice(invoice: Invoice): Observable<any> {
        return this.http.patch(this.getClientUrl(), invoice);
    }

    getClientsInvoices(): Observable<Invoice[]> {
        return this.http.get<Invoice[]>(this.getClientUrl());
    }

    private getClientUrl(): string {
        return this.urlBuilder.getUrlForEndPoint("clientInvoices");
    }

    resetOrders(): void {
        this.orders_.splice(0);
        this.orderLength.next(0);
        this.orderCount = 0;
    }

    isSelected(invoice?: Invoice): boolean {
        return invoice ? this.selectedInvoices.includes(invoice) : false;
    }

    updateInvoiceSelection(invoice: Invoice): void {
        let index = this.selectedInvoices.indexOf(invoice);

        if (index >= 0) {
            this.selectedInvoices.splice(index, 1);
        } else {
            this.selectedInvoices.push(invoice);
        }
    }
    getSelectedInvoices(): Invoice[]{
        return this.selectedInvoices;
    }

    resetSelection():void {
        this.selectedInvoices.splice(0);
    }
}