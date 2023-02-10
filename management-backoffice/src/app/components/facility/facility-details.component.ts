import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {Observable, Subscription, tap} from "rxjs";
import {FacilityService} from "./facility.service";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {Facility} from "../../model/navigation.model";
import {NEW_ITEM} from "../navigation/navigation.constants";

@Component({
    selector: 'mb-facility-details',
    templateUrl: './facility-details.component.html'
})
export class FacilityDetailsComponent implements OnInit, OnDestroy {
    facilityForm: FormGroup = this.createFrom("", "", "", "");
    $facility: Observable<Facility> = new Observable<Facility>();
    private subscriptions: Subscription = new Subscription();


    constructor(
        private topNavService: TopNavService,
        private facilityService: FacilityService,
        private breadService: BreadcrumbsService,
        private formBuilder: FormBuilder
    ) {
    }

    ngOnInit(): void {
        let code = this.breadService.pages.details;

        if (code && code !== NEW_ITEM) {
            this.$facility = this.facilityService.getFullFacilityByCode(code).pipe(
                tap(facility => {
                    let {code, name, alias, address} = facility;
                    this.facilityForm = this.createFrom(code, name || "", alias || "", address || "");
                    this.subscribeToForm();
                })
            );
        }
        else{
            this.subscribeToForm();
        }
    }

    private subscribeToForm() {
        let subscription = this.facilityForm.valueChanges.subscribe({
            next: value => {
                this.topNavService.formValues = value;
                this.topNavService.$formChanged.next(true);
            }
        });
        this.subscriptions.add(subscription);
    }

    private createFrom(code: string, name: string, alias: string, address: string) {
        return this.formBuilder.group({
            code: [code],
            name: [name],
            alias: [alias],
            address: [address]
        });
    }

    ngOnDestroy(): void {
        this.topNavService.formValues = {};
        this.subscriptions.unsubscribe();
    }

}
