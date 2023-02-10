import {NgModule} from '@angular/core';
import {NavigationModule} from "./navigation/navigation.module";
import {ListingModule} from "./listing/listing.module";
import {FacilityModule} from "./facility/facility.module";



@NgModule({
    declarations: [
  ],
    imports: [
        NavigationModule,
        ListingModule,
        FacilityModule
    ],
    exports: [
        NavigationModule,
    ]
})
export class ComponentsModule {
}
