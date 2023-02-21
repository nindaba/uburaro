import { NgModule } from '@angular/core';
import {RentDetailsComponent} from "./rent-details.component";
import {AppCommonModule} from "../../app-common.module";
import {RentListingComponent} from "./rent-listing.component";
import {ListingModule} from "../listing/listing.module";



@NgModule({
  declarations: [RentDetailsComponent,RentListingComponent],
  imports: [
    AppCommonModule,
    ListingModule
  ],
  exports: [RentDetailsComponent,RentListingComponent]
})
export class RentModule { }
