import {Injectable, OnDestroy} from "@angular/core";
import {TopNavService} from "./navigation/top-nav/top-nav.service";
import {FormControl, FormGroup} from "@angular/forms";
import {Subscription} from "rxjs";
import {Capital, Facility} from "../model/navigation.model";
import {Router} from "@angular/router";

@Injectable()
export class AbstractDetailsComponent implements OnDestroy {
    private subscriptions: Subscription = new Subscription();
    itemForm: FormGroup = new FormGroup<any>([]);

    constructor(protected topNavService: TopNavService, protected router: Router) {
    }

    protected subscribeToForm() {
        this.topNavService.itemForm = this.itemForm;
        let subscription = this.itemForm.valueChanges.subscribe({
            next: () => {
                this.topNavService.$formChanged.next(true);
            }
        });
        this.subscriptions.add(subscription);
    }

    ngOnDestroy(): void {
        this.topNavService.itemForm?.reset();
        this.subscriptions.unsubscribe();
    }

    subscribeToDelete(redirect: string = "/") {
        this.subscriptions.add(this.topNavService
            .$delete.subscribe({next: () => this.router.navigate([redirect])}));

    }

}