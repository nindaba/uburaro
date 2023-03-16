import {Component} from '@angular/core';
import {filter, Observable, tap} from "rxjs";
import {Invoice} from "../../model/navigation.model";
import {InvoiceService} from "./invoice.service";
import {NotificationService} from "../notification/notification.service";
import {NotificationKeys} from "../../config/notifications.config";
import {TopNavService} from "../navigation/top-nav/top-nav.service";

@Component({
    selector: 'mb-invoices',
    templateUrl: './invoices.component.html'
})
export class InvoicesComponent {
    $invoices: Observable<Invoice[]> = this.invoiceService.getClientsInvoices();
    $notif: Observable<any> = new Observable();

    constructor(protected invoiceService: InvoiceService,
                protected notification: NotificationService,
                protected topService: TopNavService) {
    }

    ngOnDestroy(): void {

    }

    ngOnInit(): void {
        this.$notif = this.notification.getNotification().pipe(
            filter(notif => [NotificationKeys.INVOICE_CREATED, NotificationKeys.DELETION_COMPLETED].includes(notif.message || "")),
            tap(() => this.$invoices = this.invoiceService.getClientsInvoices()),
            tap(()=> this.invoiceService.resetSelection())
        )
    }

}
