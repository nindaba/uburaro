import { Component } from '@angular/core';
import {Observable} from "rxjs";
import {Invoice} from "../../model/navigation.model";
import {InvoiceService} from "./invoice.service";

@Component({
  selector: 'mb-invoices',
  templateUrl: './invoices.component.html'
})
export class InvoicesComponent {
    $invoices: Observable<Invoice[]> =  this.invoiceService.getClientsInvoices();

    constructor(protected invoiceService:InvoiceService) {
    }

}
