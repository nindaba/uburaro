import {Component, Input, OnDestroy, OnInit} from "@angular/core";
import {CommonModule, DatePipe} from "@angular/common";
import {RelationComponent} from "../relation/relation.component";
import {TotalPipe} from "../../pipes/total.pipe";
import {TotalCostPipe} from "../../pipes/total-cost.pipe";
import {TranslateModule} from "@ngx-translate/core";
import {BehaviorSubject, Subject, Subscription} from "rxjs";
import {RentContract} from "../../model/navigation.model";
import {OrderModule} from "../order/order.module";

@Component({
    selector: 'mb-rent-contract',
    templateUrl: './rent-contract.component.html',
    standalone: true,
    imports: [
        DatePipe, CommonModule, RelationComponent, TotalPipe, TotalCostPipe, TranslateModule, OrderModule
    ]
})
export class RentContractComponent implements OnInit, OnDestroy {
    subscription: Subscription = new Subscription();

    @Input()
    contract: RentContract | undefined;
    @Input()
    showAll: Subject<boolean> = new BehaviorSubject(false);


    ngOnDestroy(): void {
    }

    ngOnInit(): void {
    }

    expand() {
        this.showAll.next(true)
    }

    shrink() {
        this.showAll.next(false)
    }
}