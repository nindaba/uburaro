import {Component} from '@angular/core';
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {mergeMap, Observable} from "rxjs";
import {CapitalEntry, Page, Pageable} from "../../model/navigation.model";
import {CapitalService} from "../facility/capital.service";
import ReportConfig from "../../../assets/content-config/report-page.json"

@Component({
  selector: 'mb-capital-report',
  templateUrl: './capital-report.component.html'
})
export class CapitalReportComponent{
    pageable: Pageable = {currentPage: 0, pageSize: 10, sort: "amount", sortOrder: "desc"};

    $entries: Observable<Page<CapitalEntry>> =this.capitalService.getCapitalEntriesPage(this.pageable);
    $ordersWithDateRange: Observable<Page<CapitalEntry>> = this.topService.dateRangeFrom.statusChanges.pipe(
        mergeMap(() => this.capitalService.getCapitalEntriesPage(this.pageable))
    );

    capitalHeads: string[] = ReportConfig.capital.heads;
    constructor(protected topService:TopNavService,protected capitalService:CapitalService) {
    }
}
