import {Component, OnInit} from '@angular/core';
import {AbstractDetailsComponent} from "../abstract-details.component";
import {BehaviorSubject, Observable, of, Subject, tap} from "rxjs";
import {CodeName, Rent, UnitType} from "../../model/navigation.model";
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
    showAll: Subject<boolean> = new BehaviorSubject(false);

    $unitTypes: Observable<CodeName[]> = of([UnitType.DAYS, UnitType.MONTHS, UnitType.YEARS].map(unit =>
        ({
            name: unit,
            code: unit
        })
    ));

    private unitFormControl: FormControl = new FormControl(UnitType.MONTHS);

    unitForm: FormGroup = new FormGroup({
        code: this.unitFormControl,
        name: new FormControl(UnitType.MONTHS)
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

    private createFrom(code: string = "", name: string = "", address: string = "",cost:number = 0,unit: UnitType = UnitType.MONTHS,currentClient:string ="") {
        this.unitFormControl.setValue(unit);
        return this.formBuilder.group({
            code: new FormControl({value: code, disabled: !!code}),
            name: new FormControl(name),
            address: new FormControl(address),
            unit: this.unitFormControl,
            cost: new FormControl(cost),
            currentClient: new FormControl({value: currentClient,disabled: true})
        });
    }

    ngOnInit(): void {
        this.itemForm = this.createFrom();
        let code = this.breadService.pages.details;

        if (code && code !== NEW_ITEM) {
            this.$rent = this.itemService.getItemByCode<Rent>(code, true).pipe(
                tap(rent => {
                    let {code, name, address,cost,unit, currentContract} = rent;
                    this.itemForm = this.createFrom(code, name, address, cost,unit,currentContract?.client?.name);
                    this.subscribeToForm();
                }),
            );

            this.subscribeToDelete(this.breadService.pages.page);
        } else {
            this.subscribeToForm();
        }
    }
}

