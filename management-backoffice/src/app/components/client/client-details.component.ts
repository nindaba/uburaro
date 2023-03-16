import {Component, OnInit} from '@angular/core';
import {AbstractDetailsComponent} from "../abstract-details.component";
import {filter, Observable, tap} from "rxjs";
import {Client, MBNotification} from "../../model/navigation.model";
import {FormBuilder, FormControl} from "@angular/forms";
import {MBItemService} from "../../services/MBItem.service";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {NEW_ITEM} from "../navigation/navigation.constants";
import {Router} from "@angular/router";
import {InvoiceService} from "./invoice.service";
import {NotificationKeys} from "../../config/notifications.config";
import {NotificationService} from "../notification/notification.service";
import {ContractService} from "../rent/contract.service";

@Component({
    selector: 'mb-client-details',
    templateUrl: './client-details.component.html',
})
export class ClientDetailsComponent extends AbstractDetailsComponent implements OnInit {
    $client: Observable<Client> = new Observable();

    $notif: Observable<MBNotification> = new Observable();

    constructor(private formBuilder: FormBuilder,
                private itemService: MBItemService,
                private breadService: BreadcrumbsService,
                protected override topNavService: TopNavService,
                protected override router: Router,
                protected invoiceService: InvoiceService,
                protected notification: NotificationService,
                protected contractService: ContractService
    ) {
        super(topNavService, router);
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
        this.loadClient();

        this.$notif = this.notification.getNotification().pipe(
            filter(notif => [NotificationKeys.DELETION_COMPLETED].includes(notif.message || "")),
            tap(() => this.loadClient()),
            tap(() => this.contractService.resetSelection())
        )
    }

    private loadClient() {
        let code = this.breadService.pages.details;
        if (code && code !== NEW_ITEM) {
            this.$client = this.itemService.getItemByCode<Client>(code, true).pipe(
                tap(client => {
                    let {code, name, address} = client;
                    this.itemForm = this.createFrom(code, name, address);
                    this.subscribeToForm();
                }),
            );
        } else {
            this.subscribeToForm();
        }

    }

    removeSelectedItems() {
        this.invoiceService.orders;
    }
}

