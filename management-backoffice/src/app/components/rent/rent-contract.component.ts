import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, Subject, Subscription} from "rxjs";
import {RentContract} from "../../model/navigation.model";
import DetailsConfig from "../../../assets/content-config/details-page.json";

@Component({
    selector: 'mb-rent-contract',
    templateUrl: './rent-contract.component.html'
})
export class RentContractComponent implements OnInit, OnDestroy {
    subscription: Subscription = new Subscription();

    @Input()
    contract: RentContract | undefined;
    creating: Subject<boolean> = new BehaviorSubject(false);
    orderHeads: string[] = DetailsConfig.contract.order.heads;

    ngOnDestroy(): void {
    }

    ngOnInit(): void {
    }

    expand() {
        this.creating.next(true)
    }

    shrink() {
        this.creating.next(false)
    }
}
