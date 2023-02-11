import {Component, OnDestroy, OnInit} from '@angular/core';
import ListingPageConfig from '../../../assets/content-config/listing-page.json'
import {FacilityService} from "./facility.service";
import {Facility} from "../../model/navigation.model";
import {filter, flatMap, map, mergeMap, Observable, Subscription, takeLast, tap} from "rxjs";
import {TopNavService} from "../navigation/top-nav/top-nav.service";

@Component({
    templateUrl: './facility-listing.component.html'
})
export class FacilityListingComponent implements OnInit, OnDestroy {
    heads: string[] = ListingPageConfig.facilities.heads;
    headerCheck: boolean = false;
    $facilities: Observable<Facility[]> = this.facilityService.getAllFacilities();

    subscriptions: Subscription = new Subscription();
    $searchResult: Observable<Facility[]> = new Observable<Facility[]>();

    constructor(private facilityService: FacilityService, private topService: TopNavService) {
    }


    selectionChanged(value: Facility) {
        this.topService.selectionChanged(value.code);
        this.headerCheck = false;
    }

    selectAll(facilities: Facility[]) {
        this.headerCheck = !this.headerCheck;
        this.topService.selectedCodes = facilities.map(value => value.code);
    }

    isSelected(facility: Facility): boolean {
        return this.topService.selectedCodes.indexOf(facility.code) > -1;
    }

    ngOnInit(): void {
        let subscription = this.topService.$delete.subscribe({
            next: () => this.$facilities = this.facilityService.getAllFacilities()
        });
        this.subscriptions.add(subscription);

        this.topService.searchForm.valueChanges.subscribe({
            next: value => this.$searchResult = this.$facilities
                .pipe(map(facilities => facilities.filter(facility => this.search(facility, value))))
        })
    }

    ngOnDestroy(): void {
        this.headerCheck = false;
        this.topService.selectedCodes = [];
        this.subscriptions.unsubscribe()
    }


    private search(facility: Facility, value: string): boolean {
        return new RegExp(value).test(JSON.stringify(facility))
    }
}
