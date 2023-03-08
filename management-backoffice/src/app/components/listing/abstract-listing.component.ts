import {Injectable, OnDestroy} from "@angular/core";
import {map, Observable, Subscription} from "rxjs";
import {Item} from "../../model/navigation.model";
import {TopNavService} from "../navigation/top-nav/top-nav.service";

@Injectable()
export abstract class AbstractListingComponent<ITEM extends Item> implements OnDestroy{
    headerCheck: boolean = false;
    subscriptions: Subscription = new Subscription();
    $searchResult: Observable<ITEM[]> = new Observable();

    protected constructor(protected topService: TopNavService) {
    }


    selectionChanged(value: ITEM) {
        this.topService.selectionChanged(value.code);
        this.headerCheck = false;
    }

    selectAll(items: ITEM[]) {
        this.headerCheck = !this.headerCheck;
        this.topService.selectedCodes = items.map(value => value.code);
    }

    isSelected(item: ITEM): boolean {
        return this.topService.selectedCodes.indexOf(item.code) > -1;
    }

    subscribeToSearch<TYPE extends ITEM>() {
        this.subscriptions.add(this.topService.searchForm.valueChanges.subscribe({
                next: (value: string) => this.$searchResult = this.getItems()
                    .pipe(map(items => items.filter(item => this.search(item, value))))
            })
        );
    }


    search(item: ITEM, value: string): boolean {
        return new RegExp(value,"i").test(JSON.stringify(item))
    }

    subscribeToDelete(){
        this.subscriptions.add(
            this.topService.$delete.subscribe({next: () => this.setItems()})
        );
    }

    abstract getItems() : Observable<ITEM[]>;
    abstract setItems() : void;

    ngOnDestroy() {
        this.headerCheck = false;
        this.topService.selectedCodes = [];
        this.subscriptions.unsubscribe();
    }
}