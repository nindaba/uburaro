import { NgModule } from '@angular/core';
import {RentDetailsComponent} from "./rent-details.component";
import {AppCommonModule} from "../../app-common.module";
import {RentListingComponent} from "./rent-listing.component";
import {ListingModule} from "../listing/listing.module";
import {ReactiveFormsModule} from "@angular/forms";
import {InputDropDownComponent} from "../drop-down/input-drop-down.component";



@NgModule({
  declarations: [RentDetailsComponent,RentListingComponent],
    imports: [
        AppCommonModule,
        ListingModule,
        ReactiveFormsModule,
        InputDropDownComponent
    ],
  exports: [RentDetailsComponent,RentListingComponent]
})
export class RentModule { }
