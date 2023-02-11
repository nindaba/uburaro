import { NgModule } from '@angular/core';
import { TopNavComponent } from './top-nav.component';
import {AppCommonModule} from "../../../app-common.module";
import { BreadcrumbsComponent } from './breadcrumbs.component';
import {ReactiveFormsModule} from "@angular/forms";



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
        ReactiveFormsModule,
    ],
})
export class TopNavModule { }
