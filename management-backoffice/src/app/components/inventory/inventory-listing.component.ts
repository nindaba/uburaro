import {Component, OnDestroy, OnInit} from '@angular/core';
import ListingPageConfig from '../../../assets/content-config/listing-page.json'
import {Inventory} from "../../model/navigation.model";
import {Observable} from "rxjs";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {AbstractListingComponent} from "../listing/abstract-listing.component";
import {MBItemService} from "../../services/MBItem.service";

@Component({
    templateUrl: './inventory-listing.component.html'
})
export class InventoryListingComponent extends AbstractListingComponent<Inventory> implements OnInit, OnDestroy {
    heads: string[] = ListingPageConfig.inventory.heads;
    $inventories: Observable<Inventory[]> = new Observable();

    constructor(private itemService: MBItemService, protected override topService: TopNavService) {
        super(topService);
    }

    ngOnInit(): void {
        this.setItems();
        this.subscribeToSearch();
        this.subscribeToSearch();
    }

    getItems(): Observable<Inventory[]> {
        return this.$inventories;
    }

    setItems(): void {
        this.$inventories = this.itemService.getItemByFacilityCode<Inventory[]>();
    }
}
