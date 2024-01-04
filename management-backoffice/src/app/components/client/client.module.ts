import {NgModule} from '@angular/core';
import {AppCommonModule} from "../../app-common.module";
import {ClientDetailsComponent} from "./client-details.component";
import {ListingModule} from "../listing/listing.module";
import {ReactiveFormsModule} from "@angular/forms";
import {ClientListingComponent} from "./client-listing.component";
import {RentContractComponent} from "../rent/rent-contract.component";
import {OrderModule} from "../order/order.module";
import {LandingPageComponent} from "./landing-page.component";
import {InvoicesComponent} from "./invoices.component";
import {CreateInvoiceComponent} from "./create-invoice.component";
import {InputDropDownComponent} from "../drop-down/input-drop-down.component";
import { InvoiceComponent } from './invoice.component';


@NgModule({
    declarations: [
        ClientDetailsComponent,
        ClientListingComponent,
        LandingPageComponent,
        InvoicesComponent,
        CreateInvoiceComponent,
        InvoiceComponent
    ],
    imports: [
        AppCommonModule,
        ListingModule,
        ReactiveFormsModule,
        RentContractComponent,
        OrderModule,
        InputDropDownComponent
    ],
    exports: [
        ClientDetailsComponent,
        ClientListingComponent
    ]
})
export class ClientModule {
}
