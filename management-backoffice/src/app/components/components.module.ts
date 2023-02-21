import {NgModule} from '@angular/core';
import {NavigationModule} from "./navigation/navigation.module";
import {ListingModule} from "./listing/listing.module";
import {FacilityModule} from "./facility/facility.module";
import {CategoryModule} from "./category/category.module";
import {InventoryModule} from "./inventory/inventory.module";
import {ClientModule} from "./client/client.module";
import {RentModule} from "./rent/rent.module";



@NgModule({
    imports: [
        NavigationModule,
        ListingModule,
        FacilityModule,
        CategoryModule,
        InventoryModule,
        ClientModule,
        RentModule
    ],
    exports: [
        NavigationModule,
    ],
})
export class ComponentsModule {
}
