import {Component, OnInit} from '@angular/core';
import {AbstractDetailsComponent} from "../abstract-details.component";
import {Observable, tap} from "rxjs";
import {Client, CodeName, InventoryOrder, Order, Rent} from "../../model/navigation.model";
import DetailsConfig from "../../../assets/content-config/details-page.json";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MBItemService} from "../../services/MBItem.service";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {OrderService} from "../inventory/order.service";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {Router} from "@angular/router";
import {NEW_ITEM} from "../navigation/navigation.constants";

@Component({
    selector: 'mb-rent-details',
    templateUrl: './rent-details.component.html'
})
export class RentDetailsComponent extends AbstractDetailsComponent implements OnInit {
    $rent: Observable<Rent> = new Observable();
    orderHeaders: string[] = DetailsConfig.client.inventory.heads;
    $orders: Observable<Order[]> = new Observable();
    $clients: Observable<Client[]> = this.itemService.getItemByFacilityCode<Client[]>("clients");

    clientCodeControl: FormControl = new FormControl('');
    clientNameControl: FormControl = new FormControl('');

    clientForm: FormGroup = new FormGroup({
        name: new FormControl(),
        code: this.clientCodeControl,
    });

    constructor(private formBuilder: FormBuilder,
                private itemService: MBItemService,
                private breadService: BreadcrumbsService,
                private orderService: OrderService,
                protected override topNavService: TopNavService,
                protected override router: Router
    ) {
        super(topNavService, router);
    }

    private createFrom(code: string = "", name: string = "", address: string = "",cost:number = 0,unit: string ="",
                       currentClient: CodeName = {
                           code: "",
                           name: ""
                       }) {
        this.clientForm.setValue(currentClient);

        return this.formBuilder.group({
            code: new FormControl({value: code, disabled: !!code}),
            name: new FormControl(name),
            address: new FormControl(address),
            unit: new FormControl(unit),
            cost: new FormControl(cost),
            currentClient: this.clientForm
        });
    }

    ngOnInit(): void {
        this.itemForm = this.createFrom();
        let code = this.breadService.pages.details;

        if (code && code !== NEW_ITEM) {
            this.$rent = this.itemService.getItemByCode<Rent>(code, true).pipe(
                tap(rent => {
                    let {code, name, address,cost,unit, currentClient} = rent;
                    this.itemForm = this.createFrom(code, name, address, cost,unit,{
                        code: currentClient?.code || "",
                        name: currentClient?.name || ""
                    });
                    this.subscribeToForm();
                }),
                // tap(value => this.$orders = this.orderService.getOrdersByRentalCode<InventoryOrder[]>(value.code))
            );

            this.subscribeToDelete(this.breadService.pages.page);
        } else {
            this.subscribeToForm();
        }
    }
}

