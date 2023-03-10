import {Component, OnInit} from '@angular/core';
import {TopNavService} from "./top-nav.service";
import {Router} from "@angular/router";
import {BreadcrumbsService} from "./breadcrumbs.service";
import {filter, Subject} from "rxjs";
import {NEW_ITEM} from "../navigation.constants";
import {FormControl, FormGroup} from "@angular/forms";
import {DateRange, NotificationStatus} from "../../../model/navigation.model";
import {NotificationService} from "../../notification/notification.service";

@Component({
    selector: 'mb-top-nav',
    templateUrl: './top-nav.component.html'
})
export class TopNavComponent implements OnInit {
    facilitySelectedClass: string = "";
    $formChanged: Subject<boolean> = this.service.$formChanged;
    searchFrom: FormControl = this.service.searchForm;

    selectedCodes: string[] = [];
    dateRangeFrom: FormGroup = new FormGroup({
        from: new FormControl(),
        to: new FormControl(),
    });

    constructor(
        private service: TopNavService,
        private router: Router,
        private breadcrumbsService: BreadcrumbsService,
        private notification: NotificationService) {
    }

    getName(nodeId: string) {
        return this.service.getName(nodeId);
    }

    isEnabled(nodeId: string) {
        return this.service.isEnabled(nodeId);
    }

    delete() {
        this.service.$confirmDelete.next(true);
    }

    add() {
        this.router.navigate([this.breadcrumbsService.pages.page])
            .then(() => this.router.navigate([this.breadcrumbsService.pages.page, NEW_ITEM]));
    }

    facilitySelected() {
        this.breadcrumbsService.setFacility();
        this.refreshFacility();
    }

    private refreshFacility() {
        this.facilitySelectedClass = this.breadcrumbsService.isFacilityInUse() ? "active" : ""
    }

    ngOnInit(): void {
        this.refreshFacility();
        this.router.events.subscribe({
            next: value => this.refreshFacility()
        })
        this.selectedCodes = this.service.selectedCodes;
        this.service.$dateRange = this.dateRangeFrom.valueChanges.pipe(
            filter((dateRange: DateRange) => new Date(dateRange.from).getTime() < new Date(dateRange.to).getTime()),
        )
    }

    discard() {
        this.service.itemForm?.reset();
        this.$formChanged.next(false);
    }

    save() {
        this.service.saveForm().subscribe({
            next: () => this.service.patched([this.breadcrumbsService.pages.page, this.service.itemForm?.get("code")?.value]),
            error: err => this.notification.notify(err.message, NotificationStatus.ERROR)
        });
    }
}
