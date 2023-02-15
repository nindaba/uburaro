import {NgModule} from '@angular/core';
import {FacilityDetailsComponent} from "./facility-details.component";
import {AppCommonModule} from "../../app-common.module";
import {FacilityListingComponent} from "./facility-listing.component";
import {ListingModule} from "../listing/listing.module";
import {FacilityService} from "./facility.service";
import {ReactiveFormsModule} from "@angular/forms";
import {FacilityServiceImpl} from "../../services/facility.service.impl";
import {CapitalService} from "./capital.service";
import {CapitalServiceImpl} from "../../services/capital.service.impl";
import {EmptyComponent} from "../empty/empty.component";


@NgModule({
    declarations: [FacilityDetailsComponent, FacilityListingComponent],
    imports: [
        AppCommonModule,
        ListingModule,
        ReactiveFormsModule,
        EmptyComponent,
    ],
    providers:[
        {provide: FacilityService, useClass:FacilityServiceImpl },
        {provide: CapitalService, useClass:CapitalServiceImpl}
    ]
})
export class FacilityModule {
}
