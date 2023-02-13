import {NgModule} from '@angular/core';
import {AppCommonModule} from "../../app-common.module";
import {CategoryDetailsComponent} from "./category-details.component";
import {CategoryListingComponent} from "./category-listing.component";
import {CategoryService} from "./category.service";
import {CategoryServiceImpl} from "../../services/category.service.impl";
import {ListingModule} from "../listing/listing.module";


@NgModule({
    declarations: [CategoryDetailsComponent, CategoryListingComponent],
    imports: [
        AppCommonModule,
        ListingModule
    ],
    exports: [
        CategoryDetailsComponent, CategoryListingComponent
    ],
    providers: [{provide: CategoryService, useClass: CategoryServiceImpl}]
})
export class CategoryModule {
}
