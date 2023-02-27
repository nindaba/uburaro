import { NgModule } from '@angular/core';
import {RentDetailsComponent} from "./rent-details.component";
import {AppCommonModule} from "../../app-common.module";
import {RentListingComponent} from "./rent-listing.component";
import {ListingModule} from "../listing/listing.module";
import {ReactiveFormsModule} from "@angular/forms";
import {InputDropDownComponent} from "../drop-down/input-drop-down.component";
import {RentContractComponent} from "./rent-contract.component";
import {CreateContractComponent} from "./create-contract.component";
import {ListContractsComponent} from "./list-contracts.component";
import {ExpandShrinkComponent} from "../expand-shrink/expand-shrink.component";



@NgModule({
  declarations: [RentDetailsComponent,RentListingComponent,RentContractComponent,CreateContractComponent,ListContractsComponent],
    imports: [
        AppCommonModule,
        ListingModule,
        ReactiveFormsModule,
        InputDropDownComponent,
        ExpandShrinkComponent
    ],
  exports: [RentDetailsComponent,RentListingComponent,ListContractsComponent]
})
export class RentModule { }
