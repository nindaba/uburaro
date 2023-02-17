import {NgModule} from '@angular/core';
import {AppCommonModule} from "../../app-common.module";
import {ClientDetailsComponent} from "./client-details.component";
import {ListingModule} from "../listing/listing.module";
import {ReactiveFormsModule} from "@angular/forms";
import {ClientListingComponent} from "./client-listing.component";


@NgModule({
    declarations: [
        ClientDetailsComponent,
        ClientListingComponent
    ],
    imports: [
        AppCommonModule,
        ListingModule,
        ReactiveFormsModule
    ],
    exports: [
        ClientDetailsComponent,
        ClientListingComponent
    ]
})
export class ClientModule {
}
