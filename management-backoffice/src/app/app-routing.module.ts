import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FacilityDetailsComponent} from "./components/facility/facility-details.component";
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
import {ClientDetailsComponent} from "./components/client/client-details.component";
import {LandingPageComponent} from "./components/client/landing-page.component";
import {InvoicesComponent} from "./components/client/invoices.component";


const routes: Routes = [
    {
        path: "",
        pathMatch: "full",
        redirectTo: "facilities" //todo: to be changed to dashboard
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
        path: "clients/:clientId",
        component: LandingPageComponent,
        children: [
            {
                path: "new",
                component: ClientDetailsComponent
            },
            {
                path: ":clientId/details",
                component: ClientDetailsComponent
            },
            {
                path: ":clientId/invoices",
                component: InvoicesComponent
            },
            {
                path: "**",
                component: ClientDetailsComponent
            },
        ]
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
    },
    {
        path: "**",
        pathMatch: "full",
        redirectTo: "facilities"
    }

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
