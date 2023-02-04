import { NgModule } from '@angular/core';
import {ListingComponent} from "./listing.component";
import {AppCommonModule} from "../../app-common.module";



@NgModule({
  declarations: [ListingComponent],
  imports: [
    AppCommonModule
  ],
  exports:[ListingComponent]
})
export class ListingModule { }
