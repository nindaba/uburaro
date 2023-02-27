import { NgModule } from '@angular/core';
import {AppCommonModule} from "../../app-common.module";
import {InventoryOrdersComponent} from "./inventory-orders.component";
import {RentOrdersComponent} from "./rent-orders.component";
import {TotalPipe} from "../../pipes/total.pipe";
import {TotalCostPipe} from "../../pipes/total-cost.pipe";



@NgModule({
  declarations: [
    RentOrdersComponent,InventoryOrdersComponent
  ],
  imports: [
    AppCommonModule,
    TotalPipe,
    TotalCostPipe
  ],
  exports:[
      RentOrdersComponent,InventoryOrdersComponent
  ]
})
export class OrderModule { }
