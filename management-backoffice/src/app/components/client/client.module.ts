import { NgModule } from '@angular/core';
import {AppCommonModule} from "../../app-common.module";
import {ClientDetailsComponent} from "./client-details.component";



@NgModule({
  declarations: [
      ClientDetailsComponent
  ],
  imports: [
    AppCommonModule
  ],
  exports:[ClientDetailsComponent]
})
export class ClientModule { }
