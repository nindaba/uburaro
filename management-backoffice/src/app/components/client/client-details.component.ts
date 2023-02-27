import {Component, OnInit} from '@angular/core';
import {AbstractDetailsComponent} from "../abstract-details.component";
import {Observable, tap} from "rxjs";
import {Category, Client, InventoryOrder} from "../../model/navigation.model";
import DetailsConfig from "../../../assets/content-config/details-page.json";
import {FormBuilder, FormControl} from "@angular/forms";
import {MBItemService} from "../../services/MBItem.service";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {NEW_ITEM} from "../navigation/navigation.constants";
import {Router} from "@angular/router";
import {OrderService} from "../inventory/order.service";

@Component({
    selector: 'mb-client-details',
    templateUrl: './client-details.component.html'
})
export class ClientDetailsComponent extends AbstractDetailsComponent implements OnInit {
    $client: Observable<Client> = new Observable();
    inventoryHeads: string[] = DetailsConfig.inventory.order.heads;
    $inventoryOrders: Observable<InventoryOrder[]> = new Observable();

    constructor(private formBuilder: FormBuilder,
                private itemService: MBItemService,
                private breadService: BreadcrumbsService,
                private orderService: OrderService,
                protected override topNavService: TopNavService,
                protected override router: Router
    ) {
        super(topNavService,router);
    }

    private createFrom(code: string = "", name: string = "", address: string = "") {
        return this.formBuilder.group({
            code: new FormControl({value: code, disabled: !!code}),
            name: new FormControl(name),
            address: new FormControl(address)
        });
    }

    ngOnInit(): void {
        this.itemForm = this.createFrom();
        let code = this.breadService.pages.details;

        if (code && code !== NEW_ITEM) {
            this.$client = this.itemService.getItemByCode<Client>(code, true).pipe(
                tap(client => {
                    let {code, name,address} = client;
                    this.itemForm = this.createFrom(code, name,address);
                    this.subscribeToForm();
                }),
                tap(value => this.$inventoryOrders = this.orderService.getOrdersByClientCode<InventoryOrder[]>(value.code))
            );

            this.subscribeToDelete(this.breadService.pages.page);
        } else {
            this.subscribeToForm();
        }
    }
}

