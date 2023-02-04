import { NgModule } from '@angular/core';
import {FacilityDetailsComponent} from "./facility-details.component";
import {AppCommonModule} from "../../app-common.module";



@NgModule({
  declarations: [FacilityDetailsComponent],
  imports: [
    AppCommonModule
  ]
})
export class FacilityModule { }
