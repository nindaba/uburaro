import { NgModule } from '@angular/core';
import { TopNavComponent } from './top-nav.component';
import {AppCommonModule} from "../../../app-common.module";
import {TopNavService} from "./top-nav.service";



@NgModule({
  declarations: [
    TopNavComponent
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
