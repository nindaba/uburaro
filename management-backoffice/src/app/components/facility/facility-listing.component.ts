import {Component, OnDestroy, OnInit} from '@angular/core';
import ListingPageConfig from '../../../assets/content-config/listing-page.json'
import {FacilityService} from "./facility.service";
import {Category, Facility} from "../../model/navigation.model";
import {Observable, Subscription} from "rxjs";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {AbstractListingComponent} from "../listing/abstract-listing.component";

@Component({
    templateUrl: './facility-listing.component.html'
})
export class FacilityListingComponent extends AbstractListingComponent<Facility> implements OnInit {
    heads: string[] = ListingPageConfig.facilities.heads;
    $facilities: Observable<Facility[]> = new Observable<Facility[]>();
    constructor(
        private facilityService: FacilityService,
        protected override topService: TopNavService) {
        super(topService);
    }


    ngOnInit(): void {
        this.setItems();
        this.subscribeToDelete();
        this.subscribeToSearch();
    }
    getItems(): Observable<Facility[]> {
        return this.$facilities;
    }

    setItems(): void {
        this.$facilities = this.facilityService.getAllFacilities();
    }

}
