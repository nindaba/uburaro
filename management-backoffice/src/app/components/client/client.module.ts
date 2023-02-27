import {NgModule} from '@angular/core';
import {AppCommonModule} from "../../app-common.module";
import {ClientDetailsComponent} from "./client-details.component";
import {ListingModule} from "../listing/listing.module";
import {ReactiveFormsModule} from "@angular/forms";
import {ClientListingComponent} from "./client-listing.component";
import {RentContractComponent} from "../rent/rent-contract.component";
import {OrderModule} from "../order/order.module";


@NgModule({
    declarations: [
        ClientDetailsComponent,
        ClientListingComponent
    ],
    imports: [
        AppCommonModule,
        ListingModule,
        ReactiveFormsModule,
        RentContractComponent,
        OrderModule
    ],
    exports: [
        ClientDetailsComponent,
        ClientListingComponent
    ]
})
export class ClientModule {
}
