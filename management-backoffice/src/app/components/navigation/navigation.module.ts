import { NgModule } from '@angular/core';
import {SideNavModule} from "./side-nav/side-nav.module";
import {TopNavModule} from "./top-nav/top-nav.module";



@NgModule({
    declarations: [
  ],
    exports: [
        SideNavModule,
        TopNavModule
    ],
    imports: [
        SideNavModule,
        TopNavModule,
    ]
})
export class NavigationModule { }
