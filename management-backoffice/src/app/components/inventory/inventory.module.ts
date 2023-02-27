import { NgModule } from '@angular/core';
import {AppCommonModule} from "../../app-common.module";
import {InventoryDetailsComponent} from "./inventory-details.component";
import {InventoryListingComponent} from "./inventory-listing.component";
import {ListingModule} from "../listing/listing.module";
import {ReactiveFormsModule} from "@angular/forms";
import { InventoryCartComponent } from './inventory-cart.component';
import {InputDropDownComponent} from "../drop-down/input-drop-down.component";
import {ExpandShrinkComponent} from "../expand-shrink/expand-shrink.component";



@NgModule({
  declarations: [InventoryDetailsComponent, InventoryListingComponent, InventoryCartComponent],
    imports: [
        AppCommonModule,
        ListingModule,
        ReactiveFormsModule,
        InputDropDownComponent,
        ExpandShrinkComponent
    ],
  exports:[
      InventoryDetailsComponent,
      InventoryListingComponent
  ],
})
export class InventoryModule { }
