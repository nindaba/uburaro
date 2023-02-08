import {NgModule} from '@angular/core';
import {FacilityDetailsComponent} from "./facility-details.component";
import {AppCommonModule} from "../../app-common.module";
import {FacilityListingComponent} from "./facility-listing.component";
import {ListingModule} from "../listing/listing.module";
import {FacilityServiceImpl} from "./facility.service.impl";
import {FacilityService} from "./facility.service";


@NgModule({
    declarations: [FacilityDetailsComponent, FacilityListingComponent],
    imports: [
        AppCommonModule,
        ListingModule
    ],
    providers:[{provide: FacilityService, useClass:FacilityServiceImpl }]
})
export class FacilityModule {
}
