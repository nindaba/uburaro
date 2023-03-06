import {Component, OnInit} from '@angular/core';
import {TopNavService} from "./top-nav.service";
import {Router} from "@angular/router";
import {BreadcrumbsService} from "./breadcrumbs.service";
import {Subject} from "rxjs";
import {NEW_ITEM} from "../navigation.constants";
import {FormControl} from "@angular/forms";

@Component({
    selector: 'mb-top-nav',
    templateUrl: './top-nav.component.html'
})
export class TopNavComponent implements OnInit {
    facilitySelectedClass: string = "";
    $formChanged: Subject<boolean> = this.service.$formChanged;
    searchFrom: FormControl = this.service.searchForm;

    constructor(private service: TopNavService, private router: Router, private breadcrumbsService: BreadcrumbsService) {
    }

    getName(nodeId: string) {
        return this.service.getName(nodeId);
    }

    isEnabled(nodeId: string) {
        return this.service.isEnabled(nodeId);
    }

    delete() {
        this.service.delete();
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
    }

    discard() {
        this.service.formValues = {};
        this.$formChanged.next(false);
    }

    save() {
        this.service.saveForm()
    }
}
