import {Component, OnDestroy, OnInit} from '@angular/core';
import ListingPageConfig from '../../../assets/content-config/listing-page.json'
import {Category, Item} from "../../model/navigation.model";
import {map, Observable, Subscription} from "rxjs";
import {CategoryService} from "./category.service";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {AbstractListingComponent} from "../listing/abstract-listing.component";

@Component({
    templateUrl: './category-listing.component.html'
})
export class CategoryListingComponent extends AbstractListingComponent<Category> implements OnInit, OnDestroy {
    heads: string[] = ListingPageConfig.category.heads;
    $categories: Observable<Category[]> = new Observable<Category[]>();

    constructor(private categoryService: CategoryService, protected override topService: TopNavService) {
        super(topService);
    }

    ngOnInit(): void {
        this.setCategories();
        this.subscriptions.add(
            this.topService.$delete.subscribe({next: () => this.setCategories()})
        );
        this.subscribeToSearch();
    }

    getItems(): Observable<Category[]> {
        return this.$categories;
    }

    setCategories() {
        this.$categories = this.categoryService.getCategoriesByFacilityCode();
    }
}
