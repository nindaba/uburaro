import {Component, OnInit} from '@angular/core';
import {AbstractDetailsComponent} from "../abstract-details.component";
import {map, Observable, tap} from "rxjs";
import {Category, CodeName, Inventory, InventoryOrder} from "../../model/navigation.model";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MBItemService} from "../../services/MBItem.service";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {NEW_ITEM} from "../navigation/navigation.constants";
import {FacilityService} from "../facility/facility.service";
import {Router} from "@angular/router";
import {OrderService} from "./order.service";

@Component({
    selector: 'mb-inventory-details',
    templateUrl: './inventory-details.component.html'
})
export class InventoryDetailsComponent extends AbstractDetailsComponent implements OnInit {
    $inventory: Observable<Inventory> = new Observable();


    categoryForm: FormGroup = new FormGroup({
        name: new FormControl("",Validators.required),
        code: new FormControl(""),
    })

    $categories: Observable<Category[]> = this.facilityService.getFacilityByCode(this.breadService.facility).pipe(
        map(value => value.categories || [])
    );

    constructor(private formBuilder: FormBuilder,
                private itemService: MBItemService,
                private breadService: BreadcrumbsService,
                protected override topNavService: TopNavService,
                private facilityService: FacilityService,
                protected override router: Router,
                protected orderService: OrderService
    ) {
        super(topNavService, router);
    }

    private createFrom(
        code: string = "", name: string = "",
        category: CodeName = {
            code: "",
            name: ""
        },
        quantity: number = 0, cost: number = 0,unit: string = "") {
        this.categoryForm.setValue(category)

        return new FormGroup({
            code: new FormControl({value: code, disabled: !!code}),
            name: new FormControl(name),
            category: this.categoryForm,
            quantity: new FormControl({value: quantity, disabled:true}),
            cost: new FormControl({value: cost, disabled: !!cost}),
            unit: new FormControl(unit)
        });
    }

    ngOnInit(): void {
        this.itemForm = this.createFrom();
        let code = this.breadService.pages.details;

        if (code && code !== NEW_ITEM) {
            this.$inventory = this.itemService.getItemByCode<Inventory>(code, true).pipe(
                tap(inventory => {
                    let {code, name, quantity, cost, category,unit} = inventory;
                    this.itemForm = this.createFrom(code, name,
                        {
                            code: category.code,
                            name: category.name || ""
                        },
                        quantity, cost,unit);
                    this.subscribeToForm();
                }),
            );
        } else {
            this.subscribeToForm();
        }
    }

}
