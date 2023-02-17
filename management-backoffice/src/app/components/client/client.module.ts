import {NgModule} from '@angular/core';
import {AppCommonModule} from "../../app-common.module";
import {ClientDetailsComponent} from "./client-details.component";
import {ClientListingComponent} from "./client-listing.component";
import {ListingModule} from "../listing/listing.module";


@NgModule({
    declarations: [
        ClientDetailsComponent,
        ClientListingComponent
    ],
    imports: [
        AppCommonModule,
        ListingModule
    ],
    exports: [
        ClientDetailsComponent,
        ClientListingComponent
    ]
})
export class ClientModule {
}
