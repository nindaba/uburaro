import {Component} from '@angular/core';

@Component({
    selector: 'mb-breadcrumbs',
    templateUrl: './breadcrumbs.component.html'
})
export class BreadcrumbsComponent {
    showFacilitySelectorClass: string = "";
    facility: string = "BreadCrumbs";
    page: string = "Facilities";
    details: string = "SNOD93ND9X3K3";
    pageId: string = "facilities";
    detailsId: string = "detailsId";
    facilityId: string = "breadcrumbs";

    toggleFacilitySelector() {
        this.showFacilitySelectorClass = this.showFacilitySelectorClass ? "" : "active";
    }
}
