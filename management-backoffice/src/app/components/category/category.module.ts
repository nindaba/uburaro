import {NgModule} from '@angular/core';
import {AppCommonModule} from "../../app-common.module";
import {CategoryDetailsComponent} from "./category-details.component";
import {CategoryListingComponent} from "./category-listing.component";
import {CategoryService} from "./category.service";
import {CategoryServiceImpl} from "../../services/category.service.impl";
import {ListingModule} from "../listing/listing.module";
import {ReactiveFormsModule} from "@angular/forms";
import {ComponentsModule} from "../components.module";
import {RelationComponent} from "../relation/relation.component";
import {EmptyComponent} from "../empty/empty.component";


@NgModule({
    declarations: [
        CategoryDetailsComponent,
        CategoryListingComponent,
    ],
    imports: [
        AppCommonModule,
        ListingModule,
        ReactiveFormsModule,
        EmptyComponent,

    ],
    exports: [
        CategoryDetailsComponent, CategoryListingComponent
    ],
    providers: [{provide: CategoryService, useClass: CategoryServiceImpl}]
})
export class CategoryModule {
}
