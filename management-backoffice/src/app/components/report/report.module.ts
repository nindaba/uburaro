import { NgModule } from '@angular/core';
import { ReportComponent } from './report.component';
import {AppCommonModule} from "../../app-common.module";



@NgModule({
  declarations: [
    ReportComponent
  ],
  imports: [
    AppCommonModule
  ],
  exports:[
      ReportComponent
  ]
})
export class ReportModule { }
