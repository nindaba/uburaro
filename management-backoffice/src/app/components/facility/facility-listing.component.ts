import {Component, OnInit} from '@angular/core';
import ListingPageConfig from '../../../assets/content-config/listing-page.json'
import {FacilityService} from "./facility.service";
import {Facility} from "../../model/navigation.model";
import {flatMap, mergeMap, Observable} from "rxjs";
import {TopNavService} from "../navigation/top-nav/top-nav.service";

@Component({
    templateUrl: './facility-listing.component.html'
})
export class FacilityListingComponent implements OnInit {
    heads: string[] = ListingPageConfig.facilities.heads;
    headerCheck: boolean = false;
    selectedFacilities: Facility[] = [];
    $facilities: Observable<Facility[]> = this.facilityService.getAllFacilities();

    constructor(private facilityService: FacilityService, private topService: TopNavService) {
    }


    selectionChanged(value: Facility) {
        let indexOf = this.selectedFacilities.indexOf(value);
        if (indexOf > -1) {
            this.selectedFacilities.splice(indexOf, 1);
        } else this.selectedFacilities.push(value);
        this.headerCheck = false;
    }

    selectAll(facilities: Facility[]) {
        this.headerCheck = !this.headerCheck;
        this.selectedFacilities = [...facilities];
    }

    isSelected(facility: Facility): boolean {
        return this.selectedFacilities.indexOf(facility) > -1;
    }

    ngOnInit(): void {
        this.topService.$delete.pipe(
            mergeMap(() => this.facilityService.deleteFacilities(this.selectedFacilities))
        ).subscribe({
            next: value => this.$facilities = this.facilityService.getAllFacilities()
        })
    }
}
