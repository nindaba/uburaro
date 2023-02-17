import {Injectable, OnDestroy} from "@angular/core";
import {TopNavService} from "./navigation/top-nav/top-nav.service";
import {FormControl, FormGroup} from "@angular/forms";
import {Subscription} from "rxjs";
import {Capital, Facility} from "../model/navigation.model";

@Injectable()
export class AbstractDetailsComponent implements OnDestroy{
    private subscriptions: Subscription = new Subscription();
    itemForm: FormGroup = new FormGroup<any>([]);
    constructor(protected topNavService: TopNavService) {
    }
    protected subscribeToForm() {
        let subscription = this.itemForm.valueChanges.subscribe({
            next: value => {
                this.topNavService.formValues = this.itemForm.getRawValue();
                this.topNavService.$formChanged.next(true);
            }
        });
        this.subscriptions.add(subscription);
    }
    ngOnDestroy(): void {
        this.topNavService.formValues = {};
        this.subscriptions.unsubscribe();
    }
}