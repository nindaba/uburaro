import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, map, mergeMap, Observable, Subscription} from "rxjs";
import {Category, CodeName, Item} from "../../model/navigation.model";
import {FormGroup, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {TranslateModule} from "@ngx-translate/core";
import {RouterModule} from "@angular/router";

@Component({
    selector: 'mb-input-drop-down',
    templateUrl: './input-drop-down.component.html',
    standalone: true,
    imports: [CommonModule, RouterModule, TranslateModule, ReactiveFormsModule]
})
export class InputDropDownComponent implements OnInit, OnDestroy {
    $showDrop: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
    subscription: Subscription = new Subscription();
    $itemSearchResults: Observable<any[]> = new Observable();

    @Input()
    $items: Observable<any[]> = new Observable();

    @Input()
    formDetails: FormGroup = new FormGroup({});

    @Input()
    relatedItem: { name: string, parentPage: string } = {
        name: '',
        parentPage: ''
    }

    ngOnInit(): void {
        this.$itemSearchResults = this.formDetails.valueChanges.pipe(
            mergeMap(value => this.$items),
            map(value => value.filter(item => this.search(item, this.formDetails.get('name')?.value))))
    }

    onFocus() {
        this.$showDrop.next(true);
    }

    onUnFocus() {
        this.subscription.unsubscribe();
        setTimeout(() => {
            this.$showDrop.next(false);
            if (this.formDetails.get("name")?.value) {
                this.undoInput();
            } else {
                this.changeInput({name: "", code: ""})
            }
        }, 300);
    }

    private undoInput() {
        this.subscription.add(
            this.$items.pipe(
                map(value => value.find(item => item.code == this.formDetails.get("code")?.value)))
                .subscribe({next: value => this.changeInput(value)})
        );
    }

    getCodeName(item: any): CodeName {
        return {
            name: "name" in item ? item.name + "" : item.code,
            code: item.code
        }
    }

    search(item: Category, value: string): boolean {
        return new RegExp(value, "i").test(JSON.stringify(item))
    }

    changeInput(item: any) {
        this.formDetails.setValue(this.getCodeName(item));
    }

    getDisplay(item: Item): string {
        if ("name" in item) {
            return item.name + "";
        }
        return item.code;
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }
}
