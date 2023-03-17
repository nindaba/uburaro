import {Component, OnInit} from '@angular/core';
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {mergeMap, Observable, tap} from "rxjs";
import {CapitalEntry, ClientReport, DateRange, NavNode} from "../../model/navigation.model";
import {CapitalService} from "../facility/capital.service";
import ReportConfig from "../../../assets/content-config/report-page.json"
@Component({
  selector: 'mb-capital-report',
  templateUrl: './capital-report.component.html'
})
export class CapitalReportComponent{
    $entries: Observable<CapitalEntry[]> =this.capitalService.getCapitalEntries();
    $ordersWithDateRange: Observable<CapitalEntry[]> = this.topService.dateRangeFrom.statusChanges.pipe(
        mergeMap(() => this.capitalService.getCapitalEntries())
    );
    capitalHeads: string[] = ReportConfig.capital.heads;
    constructor(protected topService:TopNavService,protected capitalService:CapitalService) {
    }
}
