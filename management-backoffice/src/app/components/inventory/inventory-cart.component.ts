import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, Observable, Subject, Subscription} from "rxjs";
import {Client, CodeName, Inventory, InventoryOrderType, NotificationStatus} from "../../model/navigation.model";
import {TranslateService} from "@ngx-translate/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MBItemService} from "../../services/MBItem.service";
import {formatDate} from "@angular/common";
import {OrderService} from "./order.service";
import {Router} from "@angular/router";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {NEW_ITEM} from "../navigation/navigation.constants";
import {NotificationService} from "../notification/notification.service";
import {HttpErrorResponse} from "@angular/common/http";
import {NotificationKeys} from "../../config/notifications.config";
import {TopNavService} from "../navigation/top-nav/top-nav.service";

@Component({
    selector: 'mb-inventory-cart',
    templateUrl: './inventory-cart.component.html'
})
export class InventoryCartComponent implements OnInit, OnDestroy {
    subscription: Subscription = new Subscription();
    $orderTypes: Observable<CodeName[]> = new BehaviorSubject(Object.values(InventoryOrderType).map(value => {
        return {name: value, code: value} as CodeName;
    }));

    @Input()
    $inventory: Observable<Inventory> = new Observable();

    orderTypeCodeControl: FormControl = new FormControl(InventoryOrderType.OUT);

    clientCodeControl: FormControl = new FormControl('');

    inventoryOrderForm: FormGroup = this.createOrderForm({
        category: {code: ""},
        code: "",
        cost: 0,
        name: "",
        quantity: 0,
        unit: ""
    })

    orderTypeForm: FormGroup = new FormGroup({
        name: new FormControl(InventoryOrderType.OUT),
        code: this.orderTypeCodeControl,
    });

    clientForm: FormGroup = new FormGroup({
        name: new FormControl('',Validators.required),
        code: this.clientCodeControl,
    });
    $clients: Observable<Client[]> = this.itemService.getItemByFacilityCode<Client[]>("clients");
    readonly soldType: InventoryOrderType = InventoryOrderType.SOLD;

    creating: Subject<boolean> = new BehaviorSubject(false);

    constructor(private translateService: TranslateService,
                private itemService: MBItemService,
                private orderService: OrderService,
                private topService: TopNavService,
                private notification:NotificationService) {
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }

    ngOnInit(): void {
        this.subscription.add(
            this.$inventory.subscribe({next: value => this.inventoryOrderForm = this.createOrderForm(value)})
        )

        this.subscription.add(
            this.orderTypeCodeControl.valueChanges.subscribe({next: value => this.toggleCost(value)})
        );
    }

    toggleCost(value: string) {
        if (value == InventoryOrderType.OUT) {
            this.inventoryOrderForm.get("cost")?.disable();
        } else {
            this.inventoryOrderForm.get("cost")?.enable();
        }
    }

    createOrderForm(inventory: Inventory) {

        return new FormGroup({
            clientCode: this.clientCodeControl,
            orderEntry: this.orderTypeCodeControl,
            quantity: new FormControl(inventory.quantity),
            orderDate: new FormControl(formatDate(new Date(), "yyyy-MM-dd", "en")),
            cost: new FormControl(inventory.cost),
            itemCode: new FormControl(inventory.code),
            itemName: new FormControl({value: inventory.name, disabled: true}),
        })
    }

    orderPlaced() {
        if(this.inventoryOrderForm.valid){
            this.subscription.add(
                this.orderService.placeOrder(this.inventoryOrderForm.getRawValue())
                    .subscribe({
                        next: () => this.notify(),
                        error: (err: HttpErrorResponse) => this.notification.notify(err.error.message,NotificationStatus.ERROR)
                    })
            );
        }
        else {
            this.notification.notify(NotificationKeys.INVALID_FORM,NotificationStatus.ERROR)
        }

    }

    private notify() {
        this.notification.notify(NotificationKeys.INVENTORY_ORDER_CREATED,NotificationStatus.SUCCESS)
        this.topService.$createdItem.emit();
    }
}
