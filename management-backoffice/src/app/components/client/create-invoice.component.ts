import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {CodeName, Order, PaymentModeType} from "../../model/navigation.model";
import {Observable, of, Subscription} from "rxjs";
import {formatDate} from "@angular/common";
import {InvoiceService} from "./invoice.service";

@Component({
    selector: 'mb-create-invoice',
    templateUrl: './create-invoice.component.html'
})
export class CreateInvoiceComponent implements OnInit ,OnDestroy{

    subscriptions: Subscription = new Subscription();
    orders: Order[] = [];
    paymentFormControl: FormControl = new FormControl(PaymentModeType.CASH);
    ordersFormControl: FormControl = new FormControl([]);
    private amountFromControl: FormControl = new FormControl(0);

    paymentForm: FormGroup = new FormGroup({
        name: new FormControl(PaymentModeType.CASH),
        code: this.paymentFormControl
    });
    paymentTypes: Observable<CodeName[]> = of([PaymentModeType.BANK, PaymentModeType.CASH, PaymentModeType.DEBT].map(payment => ({
        code: payment,
        name: payment
    })));
    invoiceForm: FormGroup = new FormGroup({
        dateCreated: new FormControl(formatDate(new Date(), "yyyy-MM-dd", "en")),
        description: new FormControl(''),
        amount: this.amountFromControl,
        orders: this.ordersFormControl,
        paymentMode: this.paymentFormControl
    });

    constructor(protected invoiceService: InvoiceService) {
    }

    createInvoice() {
        this.subscriptions.add(
            this.invoiceService.createInvoice(this.invoiceForm.getRawValue()).subscribe({next: value => this.onSuccess()})
        )
    }

    ngOnInit(): void {
        this.orders = this.invoiceService.orders;
        this.ordersFormControl.setValue(this.orders.map(order => ({ orderNumber: order.orderNumber})));
        this.amountFromControl.setValue(this.orders.reduce((acc, order) => acc + order.cost * order.quantity, 0));
    }

    ngOnDestroy(): void {
        this.subscriptions.unsubscribe();
    }

    remove(order: Order) {
        this.invoiceService.removeOrder(order);
    }

    private onSuccess() {
        this.amountFromControl.reset()
        this.ordersFormControl.setValue([]);
        this.invoiceForm.reset();
        this.invoiceService.orders = [];
        this.orders = [];
    }
}
