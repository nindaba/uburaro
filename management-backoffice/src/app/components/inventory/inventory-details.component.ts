import {Component, OnInit} from '@angular/core';
import {AbstractDetailsComponent} from "../abstract-details.component";
import {Observable, tap} from "rxjs";
import {Category, Inventory} from "../../model/navigation.model";
import DetailsConfig from "../../../assets/content-config/details-page.json";
import {FormBuilder} from "@angular/forms";
import {MBItemService} from "../../services/MBItem.service";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {NEW_ITEM} from "../navigation/navigation.constants";

@Component({
    selector: 'mb-inventory-details',
    templateUrl: './inventory-details.component.html'
})
export class InventoryDetailsComponent extends AbstractDetailsComponent implements OnInit {
    $inventory: Observable<Inventory> = new Observable();
    orders: string[] = DetailsConfig.category.inventory.heads;

    constructor(private formBuilder: FormBuilder,
                private itemService: MBItemService,
                private breadService: BreadcrumbsService,
                protected override topNavService: TopNavService
    ) {
        super(topNavService);
    }

    private createFrom(code: string = "", name: string = "", category: string = "", quantity: number = 0, cost: number = 0) {
        return this.formBuilder.group({
            code: {value: code, disabled: code},
            name: [name],
            category: [category],
            quantity: {value: quantity, disabled: quantity},
            cost: {value: cost, disabled: cost}
        });
    }

    ngOnInit(): void {
        this.itemForm = this.createFrom();
        let code = this.breadService.pages.details;

        if (code && code !== NEW_ITEM) {
            this.$inventory = this.itemService.getItemByCode<Inventory>(code, true).pipe(
                tap(inventory => {
                    let {code, name, quantity, cost, category} = inventory;
                    this.itemForm = this.createFrom(code, name, category.code, quantity, cost);
                    this.subscribeToForm();
                }),
            );
        } else {
            this.subscribeToForm();
        }
    }
}
