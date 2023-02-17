import {Component, OnInit} from '@angular/core';
import {AbstractDetailsComponent} from "../abstract-details.component";
import {BehaviorSubject, delay, filter, map, mergeMap, Observable, tap} from "rxjs";
import {Category, Facility, Inventory} from "../../model/navigation.model";
import DetailsConfig from "../../../assets/content-config/details-page.json";
import {FormBuilder, FormControl, FormControlState, FormGroup} from "@angular/forms";
import {MBItemService} from "../../services/MBItem.service";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {NEW_ITEM} from "../navigation/navigation.constants";
import {FacilityService} from "../facility/facility.service";

@Component({
    selector: 'mb-inventory-details',
    templateUrl: './inventory-details.component.html'
})
export class InventoryDetailsComponent extends AbstractDetailsComponent implements OnInit {
    $inventory: Observable<Inventory> = new Observable();
    orders: string[] = DetailsConfig.category.inventory.heads;

    categoryNameControl: FormControl = new FormControl('');
    categoryCodeControl: FormControl = new FormControl('');

    $showCategoryDrop: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

    $categories: Observable<Category[]> = this.facilityService.getFacilityByCode(this.breadService.facility).pipe(
        map(value => value.categories || [])
    );
    $categorySearchResults: Observable<Category[]> = new Observable<Category[]>();

    constructor(private formBuilder: FormBuilder,
                private itemService: MBItemService,
                private breadService: BreadcrumbsService,
                protected override topNavService: TopNavService,
                private facilityService: FacilityService
    ) {
        super(topNavService);
    }

    private createFrom(code: string = "", name: string = "", category: Category = {code: ""}, quantity: number = 0, cost: number = 0) {
        this.categoryNameControl.setValue(category.name);
        this.categoryCodeControl.setValue(category.code);
        return new FormGroup({
            code: new FormControl({value: code, disabled: !!code}),
            name: new FormControl(name),
            category: new FormGroup({
                name: this.categoryNameControl,
                code: this.categoryCodeControl,
            }),
            quantity: new FormControl({value: quantity, disabled: !!quantity}),
            cost: new FormControl({value: cost, disabled: !!cost})
        });
    }

    ngOnInit(): void {
        this.itemForm = this.createFrom();
        let code = this.breadService.pages.details;

        if (code && code !== NEW_ITEM) {
            this.$inventory = this.itemService.getItemByCode<Inventory>(code, true).pipe(
                tap(inventory => {
                    let {code, name, quantity, cost, category} = inventory;
                    this.itemForm = this.createFrom(code, name, category, quantity, cost);
                    this.subscribeToForm();
                }),
            );
        } else {
            this.subscribeToForm();
        }
        this.$categorySearchResults = this.categoryNameControl.valueChanges.pipe(
            mergeMap(value => this.$categories),
            map(value => value.filter(category => this.search(category, this.categoryNameControl.value)))
        )
    }

    onFocus() {
        this.$showCategoryDrop.next(true);
    }

    onUnFocus() {
        setTimeout(() => this.$showCategoryDrop.next(false),100)
    }

    search(item: Category, value: string): boolean {
        return new RegExp(value,"i").test(JSON.stringify(item))
    }

    changeCategory(category: Category){
        this.categoryNameControl.setValue(category.name);
        this.categoryCodeControl.setValue(category.code);
    }

}
