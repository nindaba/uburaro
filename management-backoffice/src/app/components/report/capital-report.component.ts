import {Component, OnInit} from '@angular/core';
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {Observable, tap} from "rxjs";
import {CapitalEntry, DateRange, NavNode} from "../../model/navigation.model";
import {CapitalService} from "../facility/capital.service";
import ReportConfig from "../../../assets/content-config/report-page.json"
@Component({
  selector: 'mb-capital-report',
  templateUrl: './capital-report.component.html'
})
export class CapitalReportComponent{
    $entries: Observable<CapitalEntry[]> = new Observable();
    $dateRange: Observable<DateRange> = this.topService.$dateRange.pipe(
        tap(daterange => this.$entries = this.capitalService.getCapitalEntries(daterange))
    );

    capitalHeads: string[] = ReportConfig.capital.heads;
    constructor(protected topService:TopNavService,protected capitalService:CapitalService) {
    }
}
