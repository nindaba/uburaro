import { NgModule } from '@angular/core';
import {RentDetailsComponent} from "./rent-details.component";
import {AppCommonModule} from "../../app-common.module";



@NgModule({
  declarations: [RentDetailsComponent],
  imports: [
    AppCommonModule
  ],
  exports: [RentDetailsComponent]
})
export class RentModule { }
