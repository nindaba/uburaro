import {NgModule} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterModule, RouterStateSnapshot, Routes, UrlMatcher} from '@angular/router';
import {ListingComponent} from "./components/listing/listing.component";
import {FacilityDetailsComponent} from "./components/facility/facility-details.component";
import {ClientDetailsComponent} from "./components/client/client-details.component";
import {CategoryDetailsComponent} from "./components/category/category-details.component";
import {InventoryDetailsComponent} from "./components/inventory/inventory-details.component";
import {RentDetailsComponent} from "./components/rent/rent-details.component";
import {ReportComponent} from "./components/report/report.component";

const listingRoutes: string[] = ["facilities", "clients", "categories", "inventories", "rents"]
const listingPageMatcher: UrlMatcher = url => {
    let consumed = {consumed: url};
    if (url.length == 1) {
        return listingRoutes.includes(url[0].path) ? consumed : null;
    }
    return null;
}

const getTitle = (url: ActivatedRouteSnapshot) => {
    if (url.url.length == 1) {
        return url.url[0].path;
    }
    if (url.url.length > 1) {
        return url.url[1].path;
    }
    return "Backoffice";
}

const routes: Routes = [
    {
        path: "",
        pathMatch: "full",
        redirectTo: "facilities/fac" //todo: to be changed to dashboard
    },
    {
        title: getTitle,
        matcher: listingPageMatcher,
        component: ListingComponent,
    },
    {
        path: "facilities/:facilityId",
        component: FacilityDetailsComponent
    },
    {
        path: "clients/:clientId",
        component: ClientDetailsComponent
    },
    {
        path: "categories/:categoryId",
        component: CategoryDetailsComponent
    },
    {
        path: "inventories/:inventoryId",
        component: InventoryDetailsComponent
    },
    {
        path: "rents/:rentId",
        component: RentDetailsComponent
    },
    {
        path: "reports",
        component: ReportComponent
    }

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}