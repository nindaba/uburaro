import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, Observable, of, Subject, Subscription} from "rxjs";
import {Client, CodeName, NotificationStatus, Rent, UnitType} from "../../model/navigation.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MBItemService} from "../../services/MBItem.service";
import {formatDate} from "@angular/common";
import {ContractService} from "./contract.service";
import {NotificationService} from "../notification/notification.service";
import {NotificationKeys} from "../../config/notifications.config";
import {HttpErrorResponse} from "@angular/common/http";


@Component({
    selector: 'mb-create-contract',
    templateUrl: './create-contract.component.html'
})
export class CreateContractComponent implements OnInit, OnDestroy {
    subscription: Subscription = new Subscription();
    creating: Subject<boolean> = new BehaviorSubject(false);

    $clients: Observable<Client[]> = this.itemService.getItemByFacilityCode<Client[]>("clients");

    $unitTypes: Observable<CodeName[]> = of([UnitType.DAYS, UnitType.MONTHS, UnitType.YEARS].map(unit =>
        ({
            name: unit,
            code: unit
        })
    ));

    unitFormControl: FormControl = new FormControl(UnitType.MONTHS);
    clientCodeControl: FormControl = new FormControl('');
    contractFileNameControl: FormControl = new FormControl("label.upload.contract");

    unitForm: FormGroup = new FormGroup({
        code: this.unitFormControl,
        name: new FormControl(UnitType.MONTHS)
    });

    clientForm: FormGroup = new FormGroup({
        name: new FormControl("",Validators.required),
        code: this.clientCodeControl,
    });
    contractForm: FormGroup = this.createForm({code: ""} as Rent);

    @Input()
    property: Observable<Rent> = new Observable();

    constructor(protected itemService: MBItemService, protected contractService: ContractService, protected notification: NotificationService) {
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }

    ngOnInit(): void {
        this.subscription.add(
            this.property.subscribe({
                next: value => this.contractForm = this.createForm(value)
            })
        )
    }

    createContract() {
        this.subscription.add(
            this.contractService.createContract(this.contractForm.getRawValue()).subscribe(
                {
                    next: value => this.onSuccess(),
                    error: (err: HttpErrorResponse) => this.notification.notify(err.error.message,NotificationStatus.ERROR)
                })
        );
    }

    private createForm(property: Rent) {
        this.unitFormControl.setValue(property.unit)
        let date = new Date();

        return new FormGroup({
            from: new FormControl(formatDate(date, "yyyy-MM-dd", "en")),
            to: new FormControl(formatDate(date.setMonth(date.getMonth() + 1), "yyyy-MM-dd", "en")),
            costPerUnit: new FormControl(property.cost),
            unit: this.unitFormControl,
            property: new FormGroup({code: new FormControl({value: property.code, disabled: true})}),
            client: this.clientForm,
            contractFileName: this.contractFileNameControl
        });
    }

    uploadContract(contractFile: HTMLInputElement) {
        if (contractFile.files) {
            let file: File = contractFile.files[0];
            this.contractFileNameControl.setValue(file.name);
        }
    }

    private onSuccess() {
        this.contractFileNameControl.setValue("label.upload.contract");
        this.contractForm.reset();
        this.notification.notify(NotificationKeys.CONTRACT_CREATED);
    }
}
