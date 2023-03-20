import {Component} from '@angular/core';
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {NavigationEnd, Router} from "@angular/router";
import {filter, map, Observable, OperatorFunction, Subject, tap} from "rxjs";
import {InvoiceService} from "../client/invoice.service";

@Component({
    selector: 'mb-confirm',
    templateUrl: './confirm.component.html'
})
export class ConfirmComponent {
    private changeCodes: OperatorFunction<any, void> = tap(() => this.items = this.topService.selectedCodes);
    routerChanged: Observable<void> = this.router.events.pipe(
        filter(ev => ev instanceof NavigationEnd),
        this.changeCodes
    );
    $onDelete: Observable<void> = this.topService.$refreshData.pipe(this.changeCodes);
    $confirmDelete: Subject<boolean> = this.topService.$confirmDelete;
    items: string[] = this.topService.selectedCodes;
    orders: string[] = this.invoiceService.orders.map(order => order.orderNumber ||"").filter(order => order);

    remove(item: string) {
        if(this.orders){
            let index = this.invoiceService.orders.findIndex(order => order.orderNumber == item);
            this.orders.splice(index, 1);
            this.invoiceService.orders.splice(index, 1);
        }
        else {
            let index = this.topService.selectedCodes.indexOf(item);
            this.items.splice(index, 1);
            this.topService.selectedCodes.splice(index, 1);
        }

    }

    constructor(
        protected topService: TopNavService,
        protected router: Router,
        protected invoiceService: InvoiceService) {
    }

    confirmed() {
      this.confirmationDone();
      this.topService.delete();
    }

    confirmationDone() {
        this.$confirmDelete.next(false);
    }
}
