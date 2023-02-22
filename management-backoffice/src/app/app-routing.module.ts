import {NgModule} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterModule, RouterStateSnapshot, Routes, UrlMatcher} from '@angular/router';
import {ListingComponent} from "./components/listing/listing.component";
import {FacilityDetailsComponent} from "./components/facility/facility-details.component";
import {ClientDetailsComponent} from "./components/client/client-details.component";
import {CategoryDetailsComponent} from "./components/category/category-details.component";
import {InventoryDetailsComponent} from "./components/inventory/inventory-details.component";
import {RentDetailsComponent} from "./components/rent/rent-details.component";
import {ReportComponent} from "./components/report/report.component";
import {FacilityListingComponent} from "./components/facility/facility-listing.component";
import {CategoryListingComponent} from "./components/category/category-listing.component";
import {InventoryListingComponent} from "./components/inventory/inventory-listing.component";
import {ClientListingComponent} from "./components/client/client-listing.component";
import {RentListingComponent} from "./components/rent/rent-listing.component";
import {CapitalReportComponent} from "./components/report/capital-report.component";
import {InventoriesReportComponent} from "./components/report/inventories-report.component";
import {RentsReportComponent} from "./components/report/rents-report.component";
import {ClientsReportComponent} from "./components/report/clients-report.component";

const listingRoutes: string[] = []
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
        redirectTo: "categories" //todo: to be changed to dashboard
    },
    {
        title: getTitle,
        matcher: listingPageMatcher,
        component: ListingComponent,
    },
    {
        path: "facilities",
        component: FacilityListingComponent
    },
    {
        path: "facilities/new",
        component: FacilityDetailsComponent
    },
    {
        path: "facilities/:facilityId",
        component: FacilityDetailsComponent
    },
    {
        path: "clients",
        component: ClientListingComponent
    },
    {
        path: "clients/new",
        component: ClientDetailsComponent
    },
    {
        path: "clients/:clientId",
        component: ClientDetailsComponent
    },
    {
        path: "categories",
        component: CategoryListingComponent
    },
    {
        path: "categories/:categoryId",
        component: CategoryDetailsComponent
    },
    {
        path: "categories/new",
        component: CategoryDetailsComponent
    },
    {
        path: "inventories",
        component: InventoryListingComponent
    },
    {
        path: "inventories/new",
        component: InventoryDetailsComponent
    },
    {
        path: "inventories/:inventoryId",
        component: InventoryDetailsComponent
    },
    {
        path: "rents",
        component: RentListingComponent
    },
    {
        path: "rents/new",
        component: RentDetailsComponent
    },
    {
        path: "rents/:rentId",
        component: RentDetailsComponent
    },
    {
        path: "reports",
        component: ReportComponent,
        children: [
            {
                path: "capital",
                component: CapitalReportComponent
            },
            {
                path: "inventories",
                component: InventoriesReportComponent,
            },
            {
                path: "clients",
                component: ClientsReportComponent
            },
            {
                path: "rents",
                component: RentsReportComponent
            },
            {
                path: "**",
                pathMatch: "full",
                redirectTo: "capital"
            }
        ]
    }

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
