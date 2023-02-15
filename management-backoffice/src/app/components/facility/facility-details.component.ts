import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {BehaviorSubject, catchError, EMPTY, map, Observable, Subject, Subscription, tap} from "rxjs";
import {FacilityService} from "./facility.service";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {Capital, CapitalType, Facility} from "../../model/navigation.model";
import {NEW_ITEM} from "../navigation/navigation.constants";
import {CapitalService} from "./capital.service";
import {Router} from "@angular/router";
import {AbstractDetailsComponent} from "../abstract-details.component";
import DetailsConfig from "../../../assets/content-config/details-page.json"

@Component({
    selector: 'mb-facility-details',
    templateUrl: './facility-details.component.html'
})
export class FacilityDetailsComponent extends AbstractDetailsComponent implements OnInit {
    $facility: Observable<Facility> = new Observable<Facility>();
    newCapital: FormControl = new FormControl<string>('')
    capitalType: CapitalType = CapitalType.INTERNAL;
    $capital: Observable<Capital > = new BehaviorSubject<Capital>({currentValue: 0});

    categoryHeads: string[] = DetailsConfig.facilities.category.heads;
    clientHeads: string[] = DetailsConfig.facilities.clients.heads;

    clientPage: string = "clients";
    categoryPage: string = "categories"


    constructor(
        protected override topNavService: TopNavService,
        private facilityService: FacilityService,
        private breadService: BreadcrumbsService,
        private formBuilder: FormBuilder,
        private capitalService: CapitalService,
        private router: Router
    ) {
        super(topNavService)
    }

    ngOnInit(): void {
        this.itemForm = this.createFrom("", "", "", "");

        let code = this.breadService.pages.details;

        if (code && code !== NEW_ITEM) {
            this.$facility = this.facilityService.getFullFacilityByCode(code).pipe(
                tap(facility => {
                    let {code, name, alias, address} = facility;
                    this.itemForm = this.createFrom(code, name || "", alias || "", address || "");
                    this.subscribeToForm();
                }),
            );

            this.$capital = this.$facility.pipe(map(facility => facility.capital || {currentValue: 0}))
        } else {
            this.subscribeToForm();
        }
    }

    private createFrom(code: string, name: string, alias: string, address: string) {
        return this.formBuilder.group({
            code: [code],
            name: [name],
            alias: [alias],
            address: [address]
        });
    }



    selectExternal() {
        this.capitalType = this.capitalType == CapitalType.EXTERNAL ? CapitalType.INTERNAL : CapitalType.EXTERNAL;
    }

    addCapital() {
        this.capitalService.addCapital(this.newCapital.value,this.capitalType)
            .subscribe({
                next: value => this.$capital = this.capitalService.getCapital()
            })
    }
}
