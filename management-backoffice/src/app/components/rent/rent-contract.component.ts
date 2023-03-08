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
    width: string = "width: 0%;"
    @Input()
    isClientPage: boolean = false;


    ngOnDestroy(): void {
    }

    ngOnInit(): void {
        if (this.contract) {
            let contractLength = this.getDateDiff(this.contract.from, this.contract.to);
            let ordered = this.getDateDiff(this.contract.from, this.contract.nextOrderDate);

            let paid = this.contract.orders?.reduce((acc, order) => acc && (order.paid || false), true);
            let progressColor = paid ? '' : '#E34A4A';
            
            this.width = `width:${ordered / contractLength * 100}%; background-color:${progressColor};`;
        }
    }

    private getDateDiff(from: Date, to: Date) {
        let contractLength = this.getTime(to) - this.getTime(new Date(from));
        return contractLength;
    }

    private getTime(date: Date): number {
        return new Date(date).getTime();
    }

    expand() {
        this.showAll.next(true)
    }

    shrink() {
        this.showAll.next(false)
    }
}