import {Component, OnInit} from '@angular/core';
import {NavigationEnd, Router, RouterEvent} from "@angular/router";
import {BehaviorSubject, filter, map, mergeMap, Observable, OperatorFunction, tap} from "rxjs";
import {BreadcrumbsService} from "./breadcrumbs.service";
import {FacilityService} from "../../facility/facility.service";
import {Facility} from "../../../model/navigation.model";
import {FormControl} from "@angular/forms";
import {NEW_ITEM} from "../navigation.constants";
import {TopNavService} from "./top-nav.service";

@Component({
    selector: 'mb-breadcrumbs',
    templateUrl: './breadcrumbs.component.html'
})
export class BreadcrumbsComponent implements OnInit {
    $showFacilitySelectorClass: Observable<string> = this.breadService.$facilitySelectorActive.pipe(map(value => value ? "active" : ""));
    $facility: BehaviorSubject<string> = this.breadService.$facility;
    pageRoute: Observable<{ page?: string; details?: string; }> = new Observable<{ page?: string; details?: string }>();
    $facilities: Observable<Facility[]> = this.facilityService.getAllFacilities();
    $searchResults: Observable<Facility[]> = new Observable<Facility[]>();
    pattern: FormControl = new FormControl<string>("");

    constructor(
        private router: Router,
        private breadService: BreadcrumbsService,
        private facilityService: FacilityService,
        private topService: TopNavService
    ) {
    }

    toggleFacilitySelector() {
        this.breadService.toggleFacilitySelector();
    }

    ngOnInit(): void {
        this.$searchResults = this.pattern.valueChanges.pipe(mergeMap(value => this.$facilities
            .pipe(map(facilities => facilities
                .filter(facility => new RegExp(value).test(JSON.stringify(facility)))))
        ));

        this.pageRoute = this.router.events.pipe(
            filter(ev => ev instanceof NavigationEnd),
            map(ev => {
                let paths: string[] = (ev as RouterEvent).url.split("?")[0].split("/");
                this.breadService.pages.page = paths[1];
                this.breadService.pages.details = paths[2];
                return this.breadService.pages;
            }),
            tap(()=> this.topService.$formChanged.next(false))
        );
    }

    selectFacility(code: string) {
        this.breadService.setFacility(code);
        this.toggleFacilitySelector();
        this.router.navigate([this.breadService.pages.page,NEW_ITEM])
            .then(value => this.router.navigate([this.breadService.pages.page]));
    }
}
