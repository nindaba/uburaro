import {NgModule} from '@angular/core';
import {NavigationModule} from "./navigation/navigation.module";
import { LogoComponent } from './logo/logo.component';
import {ListingModule} from "./listing/listing.module";
import {FacilityModule} from "./facility/facility.module";



@NgModule({
    declarations: [
    LogoComponent
  ],
    imports: [
        NavigationModule,
        ListingModule,
        FacilityModule
    ],
    exports: [
        NavigationModule,
        LogoComponent,
        ListingModule,
        FacilityModule
    ]
})
export class ComponentsModule {
}
