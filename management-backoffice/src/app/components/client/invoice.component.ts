import {Component, Input} from '@angular/core';
import {Invoice} from "../../model/navigation.model";
import {BehaviorSubject, Subject} from "rxjs";

@Component({
    selector: 'mb-invoice',
    templateUrl: './invoice.component.html'
})
export class InvoiceComponent {
    @Input()
    invoice: Invoice | undefined;
    showAll: Subject<boolean> = new BehaviorSubject(false);

    expand() {
        this.showAll.next(true);
    }
    shrink(){
        this.showAll.next(false);
    }
}
