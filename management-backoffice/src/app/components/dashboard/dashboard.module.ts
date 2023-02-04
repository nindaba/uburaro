import { NgModule } from '@angular/core';
import {DashboardComponent} from "./dashboard.component";
import {AppCommonModule} from "../../app-common.module";



@NgModule({
  declarations: [DashboardComponent],
  imports: [
    AppCommonModule
  ],
  exports:[
      DashboardComponent
  ]
})
export class DashboardModule { }
