import {NgModule} from '@angular/core';
import {ReportComponent} from './report.component';
import {AppCommonModule} from "../../app-common.module";
import {CapitalReportComponent} from "./capital-report.component";
import {InventoriesReportComponent} from "./inventories-report.component";
import {RentsReportComponent} from "./rents-report.component";
import {ClientsReportComponent} from "./clients-report.component";
import {TotalPipe} from "../../pipes/total.pipe";
import {CapitalEntryAmountPipe} from "../../pipes/capital-entry-amount.pipe";


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
        TotalPipe,
        CapitalEntryAmountPipe,
    ],
})
export class ReportModule {
}
