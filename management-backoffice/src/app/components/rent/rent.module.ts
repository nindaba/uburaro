import { NgModule } from '@angular/core';
import {RentDetailsComponent} from "./rent-details.component";
import {AppCommonModule} from "../../app-common.module";
import {RentListingComponent} from "./rent-listing.component";
import {ListingModule} from "../listing/listing.module";
import {ReactiveFormsModule} from "@angular/forms";
import {InputDropDownComponent} from "../drop-down/input-drop-down.component";
import {CreateContractComponent} from "./create-contract.component";
import {ExpandShrinkComponent} from "../expand-shrink/expand-shrink.component";
import {TotalPipe} from "../../pipes/total.pipe";
import {TotalCostPipe} from "../../pipes/total-cost.pipe";
import {RentContractComponent} from "./rent-contract.component";
import {OrderModule} from "../order/order.module";



@NgModule({
  declarations: [RentDetailsComponent,RentListingComponent,CreateContractComponent],
    imports: [
        AppCommonModule,
        ListingModule,
        ReactiveFormsModule,
        InputDropDownComponent,
        ExpandShrinkComponent,
        RentContractComponent,
        OrderModule
    ],
  exports: [RentDetailsComponent,RentListingComponent]
})
export class RentModule { }
