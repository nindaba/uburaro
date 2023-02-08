import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
    selector: 'mb-listing',
    templateUrl: './listing.component.html'
})
export class ListingComponent{
    @Input()
    heads: string[] = [];
    @Input()
    checkedAll : boolean = false;
    @Output()
    selectedAll: EventEmitter<void> = new EventEmitter<void>();


    selectAll() {
        this.selectedAll.emit();
    }
}
