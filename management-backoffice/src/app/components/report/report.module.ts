import {NgModule} from '@angular/core';
import {ReportComponent} from './report.component';
import {AppCommonModule} from "../../app-common.module";
import {CapitalReportComponent} from "./capital-report.component";
import {InventoriesReportComponent} from "./inventories-report.component";
import {RentsReportComponent} from "./rents-report.component";
import {ClientsReportComponent} from "./clients-report.component";


@NgModule({
    declarations: [
        ReportComponent,
        CapitalReportComponent,
        InventoriesReportComponent,
        RentsReportComponent,
        ClientsReportComponent
    ],
    imports: [
        AppCommonModule,
    ],
})
export class ReportModule {
}
