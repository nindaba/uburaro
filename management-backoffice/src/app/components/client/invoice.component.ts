import {Component, Input} from '@angular/core';
import {Invoice, Order} from "../../model/navigation.model";
import {BehaviorSubject, Subject} from "rxjs";
import {InvoiceService} from "./invoice.service";

@Component({
    selector: 'mb-invoice',
    templateUrl: './invoice.component.html'
})
export class InvoiceComponent {
    @Input()
    invoice: Invoice | undefined;
    showAll: Subject<boolean> = new BehaviorSubject(false);

    constructor(private invoiceService:InvoiceService) {
    }

    expand() {
        this.showAll.next(true);
    }
    shrink(){
        this.showAll.next(false);
    }

    selectInvoice() {
        if(this.invoice){
            this.invoiceService.updateInvoiceSelection(this.invoice)
        }
    }

    isSelected():boolean {
        return this.invoiceService.isSelected(this.invoice);
    }
}
