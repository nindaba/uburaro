import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, Observable, Subscription} from "rxjs";
import {Client, CodeName, Inventory, InventoryOrder, InventoryOrderType} from "../../model/navigation.model";
import {TranslateService} from "@ngx-translate/core";
import {FormControl, FormGroup} from "@angular/forms";
import {MBItemService} from "../../services/MBItem.service";
import {formatDate} from "@angular/common";
import {Router} from "@angular/router";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {NEW_ITEM} from "../navigation/navigation.constants";

@Component({
    selector: 'mb-list-contract',
    templateUrl: './list-contracts.component.html'
})
export class ListContractsComponent implements OnInit, OnDestroy {
    subscription: Subscription = new Subscription();

    ngOnDestroy(): void {
    }

    ngOnInit(): void {
    }

}
