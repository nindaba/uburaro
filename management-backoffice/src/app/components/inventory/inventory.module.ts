import { NgModule } from '@angular/core';
import {AppCommonModule} from "../../app-common.module";
import {InventoryDetailsComponent} from "./inventory-details.component";
import {InventoryListingComponent} from "./inventory-listing.component";
import {ListingModule} from "../listing/listing.module";



@NgModule({
  declarations: [InventoryDetailsComponent, InventoryListingComponent],
  imports: [
    AppCommonModule,
    ListingModule
  ],
  exports:[
      InventoryDetailsComponent,
      InventoryListingComponent
  ],
})
export class InventoryModule { }
