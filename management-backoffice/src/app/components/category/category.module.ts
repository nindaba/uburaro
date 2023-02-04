import { NgModule } from '@angular/core';
import {AppCommonModule} from "../../app-common.module";
import {CategoryDetailsComponent} from "./category-details.component";



@NgModule({
  declarations: [CategoryDetailsComponent],
  imports: [
    AppCommonModule
  ],
  exports:[
      CategoryDetailsComponent
  ]
})
export class CategoryModule { }
