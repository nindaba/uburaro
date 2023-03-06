import {Component, OnDestroy, OnInit} from '@angular/core';
import {filter, Observable, tap} from "rxjs";
import {Invoice} from "../../model/navigation.model";
import {InvoiceService} from "./invoice.service";
import {NotificationService} from "../notification/notification.service";
import {NotificationKeys} from "../../config/notifications.config";

@Component({
  selector: 'mb-invoices',
  templateUrl: './invoices.component.html'
})
export class InvoicesComponent{
    $invoices: Observable<Invoice[]> =  this.invoiceService.getClientsInvoices();
    $notif: Observable<any> = new Observable();
    constructor(protected invoiceService:InvoiceService, protected notification:NotificationService) {
    }

    ngOnDestroy(): void {

    }

    ngOnInit(): void {
        this.$notif = this.notification.getNotification().pipe(
            filter(notif => notif.message == NotificationKeys.INVOICE_CREATED),
            tap(notif => this.$invoices =  this.invoiceService.getClientsInvoices())
        )
    }

}
