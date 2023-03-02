import {Invoice, Order} from "../../model/navigation.model";
import {Observable, Subject} from "rxjs";

export abstract class InvoiceService{
    /**
     * Adds all the invoice orders which are selected to be sent for invoicing
     *
     * @param orders
     */
    abstract set orders(orders: Order[]);
    /**
     * Gets all the invoice orders which are selected to be sent for invoicing
     *
     * @return orders
     */
    abstract get orders(): Order[];

    /**
     * gets the new invoice orders
     *
     * @return subject
     */
    abstract getOrderCount(): Subject<number>;

    /**
     * Adds a count to the invoices orders
     *
     * @param count
     */
    abstract addOrderCount(count: number):void;

    /**
     * removes an order from new invoice orders
     *
     * @param order
     */
    abstract removeOrder(order: Order): void;

    /**
     * Sends the invoice to the backed for processing
     *
     * @param invoice
     */
    abstract createInvoice(invoice:Invoice): Observable<any>;

    /**
     * Fetches all the invoices for the current client
     */
    abstract getClientsInvoices(): Observable<Invoice[]>;
}