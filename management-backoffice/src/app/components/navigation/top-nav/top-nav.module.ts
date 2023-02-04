import { NgModule } from '@angular/core';
import { TopNavComponent } from './top-nav.component';
import {AppCommonModule} from "../../../app-common.module";
import {TopNavService} from "./top-nav.service";
import { BreadcrumbsComponent } from './breadcrumbs.component';



@NgModule({
  declarations: [
    TopNavComponent,
    BreadcrumbsComponent
  ],
  exports: [
    TopNavComponent
  ],
  imports: [
    AppCommonModule,
  ],
  providers: [TopNavService]
})
export class TopNavModule { }
