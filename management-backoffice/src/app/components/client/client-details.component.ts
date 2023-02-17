import {Component, OnInit} from '@angular/core';
import {AbstractDetailsComponent} from "../abstract-details.component";
import {Observable, tap} from "rxjs";
import {Category, Client} from "../../model/navigation.model";
import DetailsConfig from "../../../assets/content-config/details-page.json";
import {FormBuilder, FormControl} from "@angular/forms";
import {MBItemService} from "../../services/MBItem.service";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {NEW_ITEM} from "../navigation/navigation.constants";

@Component({
    selector: 'mb-client-details',
    templateUrl: './client-details.component.html'
})
export class ClientDetailsComponent extends AbstractDetailsComponent implements OnInit {
    $client: Observable<Client> = new Observable();
    inventoryHeads: string[] = DetailsConfig.category.inventory.heads;

    constructor(private formBuilder: FormBuilder,
                private itemService: MBItemService,
                private breadService: BreadcrumbsService,
                protected override topNavService: TopNavService
    ) {
        super(topNavService);
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
            );
        } else {
            this.subscribeToForm();
        }
    }
}

