import {NgModule} from '@angular/core';
import {NavigationModule} from "./navigation/navigation.module";
import {ListingModule} from "./listing/listing.module";
import {FacilityModule} from "./facility/facility.module";
import {CategoryModule} from "./category/category.module";



@NgModule({
    imports: [
        NavigationModule,
        ListingModule,
        FacilityModule,
        CategoryModule
    ],
    exports: [
        NavigationModule,
    ],
})
export class ComponentsModule {
}
