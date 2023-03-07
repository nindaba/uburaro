import {Component} from '@angular/core';
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {NavigationEnd, Router} from "@angular/router";
import {filter, Observable, Subject, tap} from "rxjs";

@Component({
    selector: 'mb-confirm',
    templateUrl: './confirm.component.html'
})
export class ConfirmComponent {
    message: string | undefined;
    routerChanged: Observable<any> = this.router.events.pipe(
        filter(ev => ev instanceof NavigationEnd),
        tap(value => this.items = this.topService.selectedCodes)
    );
    $confirmDelete: Subject<boolean> = this.topService.$confirmDelete;
    items: string[] = this.topService.selectedCodes;

    remove(item: string) {
        let index = this.topService.selectedCodes.indexOf(item);
        this.items.splice(index, 1);
        this.topService.selectedCodes.splice(index, 1);
    }

    constructor(protected topService: TopNavService, protected router: Router) {
    }

    confirmed() {
      this.confirmationDone();
      this.topService.delete();
    }

    confirmationDone() {
        this.$confirmDelete.next(false);
        this.items = this.topService.selectedCodes = [];
    }
}
