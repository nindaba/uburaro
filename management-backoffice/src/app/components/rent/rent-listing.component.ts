import {Component, OnDestroy, OnInit} from '@angular/core';
import ListingPageConfig from '../../../assets/content-config/listing-page.json'
import {Category, Facility, Rent} from "../../model/navigation.model";
import {Observable, Subscription} from "rxjs";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {AbstractListingComponent} from "../listing/abstract-listing.component";
import {MBItemService} from "../../services/MBItem.service";

@Component({
    templateUrl: './rent-listing.component.html'
})
export class RentListingComponent extends AbstractListingComponent<Rent> implements OnInit {
    heads: string[] = ListingPageConfig.rent.heads;
    $rents: Observable<Rent[]> = new Observable();
    constructor(
        protected itemService: MBItemService,
        protected override topService: TopNavService) {
        super(topService);
    }


    ngOnInit(): void {
        this.setItems();
        this.subscribeToDelete();
        this.subscribeToSearch();
    }
    getItems(): Observable<Rent[]> {
        return this.$rents;
    }

    setItems(): void {
        this.$rents = this.itemService.getItemByFacilityCode<Rent[]>();
    }

}
