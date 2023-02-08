import {Component, OnInit} from '@angular/core';
import {NavigationEnd, Router, RouterEvent} from "@angular/router";
import {filter, map, Observable} from "rxjs";
import {BreadcrumbsService} from "./breadcrumbs.service";

@Component({
    selector: 'mb-breadcrumbs',
    templateUrl: './breadcrumbs.component.html'
})
export class BreadcrumbsComponent implements OnInit {
    showFacilitySelectorClass: string = "";
    facility: string = "BreadCrumbs";
    pageRoute: Observable<{ page?: string; details?: string; }> = new Observable<{page?: string; details?: string}>();

    constructor(private router: Router, private breadService: BreadcrumbsService) {
    }

    toggleFacilitySelector() {
        this.showFacilitySelectorClass = this.showFacilitySelectorClass ? "" : "active";
    }

    ngOnInit(): void {
        this.pageRoute = this.router.events.pipe(
            filter(ev => ev instanceof NavigationEnd),
            map(ev => {
                let paths: string[] = (ev as RouterEvent).url.split("?")[0].split("/");
                this.breadService.pages.page =  paths[1];
                this.breadService.pages.details =  paths[2];
                return this.breadService.pages;
            })
        );
    }
}
