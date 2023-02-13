import {Injectable, OnDestroy} from "@angular/core";
import {map, Observable, Subscription} from "rxjs";
import {Facility, Item} from "../../model/navigation.model";
import {TopNavService} from "../navigation/top-nav/top-nav.service";

@Injectable()
export abstract class AbstractListingComponent implements OnDestroy{
    headerCheck: boolean = false;
    subscriptions: Subscription = new Subscription();
    $searchResult: Observable<Facility[]> = new Observable<Facility[]>();

    constructor(protected topService: TopNavService) {
    }


    selectionChanged(value: Item) {
        this.topService.selectionChanged(value.code);
        this.headerCheck = false;
    }

    selectAll(items: Item[]) {
        this.headerCheck = !this.headerCheck;
        this.topService.selectedCodes = items.map(value => value.code);
    }

    isSelected(item: Item): boolean {
        return this.topService.selectedCodes.indexOf(item.code) > -1;
    }

    subscribeToSearch<TYPE extends Item>() {
        this.subscriptions.add(this.topService.searchForm.valueChanges.subscribe({
                next: (value: string) => this.$searchResult = this.getItems()
                    .pipe(map(items => items.filter(item => this.search(item, value))))
            })
        );
    }


    search(item: any, value: string): boolean {
        return new RegExp(value).test(JSON.stringify(item))
    }

    abstract getItems<ITEM extends Item>() : Observable<ITEM[]>;

    ngOnDestroy() {
        this.headerCheck = false;
        this.topService.selectedCodes = [];
        this.subscriptions.unsubscribe();
    }
}