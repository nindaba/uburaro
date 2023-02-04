import { NgModule } from '@angular/core';
import {AppCommonModule} from "../../app-common.module";
import {InventoryDetailsComponent} from "./inventory-details.component";



@NgModule({
  declarations: [InventoryDetailsComponent],
  imports: [
    AppCommonModule
  ],
  exports:[
      InventoryDetailsComponent
  ]
})
export class InventoryModule { }
