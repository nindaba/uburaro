import {NgModule} from '@angular/core';
import {SideNavComponent} from "./side-nav.component";
import { NavNodeComponent } from './nav-node.component';
import {SideNavService} from "./side-nav.service";
import {AppCommonModule} from "../../../app-common.module";


@NgModule({
    declarations: [
        SideNavComponent,
        NavNodeComponent
    ],
    imports: [
        AppCommonModule,
        ],
    exports: [
        SideNavComponent
    ],
    providers:[SideNavService]
})
export class SideNavModule {
}
