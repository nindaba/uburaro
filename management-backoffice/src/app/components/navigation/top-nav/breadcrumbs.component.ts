import {Component, OnInit} from '@angular/core';
import {NavigationEnd, Router, RouterEvent} from "@angular/router";
import {filter, map, Observable} from "rxjs";

@Component({
    selector: 'mb-breadcrumbs',
    templateUrl: './breadcrumbs.component.html'
})
export class BreadcrumbsComponent implements OnInit {
    showFacilitySelectorClass: string = "";
    facility: string = "BreadCrumbs";
    pageRoute: Observable<{ page?: string; details?: string; }> = new Observable<{page?: string; details?: string}>();
    pageId: string = "facilities";
    detailsId: string = "detailsId";
    facilityId: string = "breadcrumbs";

    constructor(private router: Router) {
    }

    toggleFacilitySelector() {
        this.showFacilitySelectorClass = this.showFacilitySelectorClass ? "" : "active";
    }

    ngOnInit(): void {
        this.pageRoute = this.router.events.pipe(
            filter(ev => ev instanceof NavigationEnd),
            map(ev => {
                let paths: string[] = (ev as RouterEvent).url.split("?")[0].split("/");
                return {
                    page: paths[1],
                    details: paths[2]
                }
            })
        );
    }
}
