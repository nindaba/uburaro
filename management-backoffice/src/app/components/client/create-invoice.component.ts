import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {CodeName, NotificationStatus, Order, PaymentModeType} from "../../model/navigation.model";
import {Observable, of, Subscription} from "rxjs";
import {formatDate} from "@angular/common";
import {InvoiceService} from "./invoice.service";
import {NotificationService} from "../notification/notification.service";
import {NotificationKeys} from "../../config/notifications.config";

@Component({
    selector: 'mb-create-invoice',
    templateUrl: './create-invoice.component.html'
})
export class CreateInvoiceComponent implements OnInit, OnDestroy {

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

    constructor(protected invoiceService: InvoiceService, protected notification: NotificationService) {
    }

    createInvoice() {
        if(this.invoiceForm.valid){
            this.subscriptions.add(
                this.invoiceService.createInvoice(this.invoiceForm.getRawValue()).subscribe({
                    next: () => this.onSuccess(),
                    error: err => this.notification.notify(err.message, NotificationStatus.ERROR)
                })
            )
        }
        else {
            this.notification.notify(NotificationKeys.INVALID_FORM,NotificationStatus.ERROR);
        }
    }

    ngOnInit(): void {
        this.orders = this.invoiceService.orders;
        this.ordersFormControl.setValue(this.orders.map(order => ({orderNumber: order.orderNumber})));
        this.amountFromControl.setValue(this.orders.reduce((acc, order) => acc + order.cost * order.quantity, 0));
    }

    ngOnDestroy(): void {
        this.subscriptions.unsubscribe();
    }

    remove(order: Order) {
        this.invoiceService.removeOrder(order);
    }

    private onSuccess() {
        this.amountFromControl.setValue(0)
        this.ordersFormControl.setValue([]);

        this.invoiceForm.setValue({
            dateCreated: formatDate(new Date(), "yyyy-MM-dd", "en"),
            orders: [],
            paymentMode: PaymentModeType.CASH,
            description: '',
            amount: 0,
        });

        this.invoiceService.resetOrders();
        this.orders.splice(0);
        this.notification.notify(NotificationKeys.INVOICE_CREATED);
    }
}
