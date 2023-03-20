import {NgModule} from '@angular/core';
import {ReportComponent} from './report.component';
import {AppCommonModule} from "../../app-common.module";
import {CapitalReportComponent} from "./capital-report.component";
import {InventoriesReportComponent} from "./inventories-report.component";
import {RentsReportComponent} from "./rents-report.component";
import {ClientsReportComponent} from "./clients-report.component";
import {CapitalEntryAmountPipe} from "../../pipes/capital-entry-amount.pipe";
import {CapitalPipe} from "../../pipes/capital.pipe";
import {ReportService} from "./report.service";
import {ReportServiceImpl} from "../../services/report.service.impl";
import {TotalPipe} from "../../pipes/total.pipe";


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
        CapitalPipe,
    ],
    providers: [{provide: ReportService, useClass: ReportServiceImpl}]
})
export class ReportModule {
}
