import {Component, OnDestroy, OnInit} from '@angular/core';
import ListingPageConfig from '../../../assets/content-config/listing-page.json'
import {Client} from "../../model/navigation.model";
import {Observable} from "rxjs";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {AbstractListingComponent} from "../listing/abstract-listing.component";
import {MBItemService} from "../../services/MBItem.service";

@Component({
    templateUrl: './client-listing.component.html'
})
export class ClientListingComponent extends AbstractListingComponent<Client> implements OnInit, OnDestroy {
    heads: string[] = ListingPageConfig.client.heads;
    $clients: Observable<Client[]> = new Observable();

    constructor(private itemService: MBItemService, protected override topService: TopNavService) {
        super(topService);
    }

    ngOnInit(): void {
        this.setInventories();
        this.subscriptions.add(
            this.topService.$delete.subscribe({next: () => this.setInventories()})
        );
        this.subscribeToSearch();
    }

    getItems(): Observable<Client[]> {
        return this.$clients;
    }

    setInventories() {
        this.$clients = this.itemService.getItemByFacilityCode<Client[]>();
    }
}
