import {Component} from '@angular/core';
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {merge, mergeMap, Observable, tap} from "rxjs";
import {CapitalEntry, Page, Pageable} from "../../model/navigation.model";
import {CapitalService} from "../facility/capital.service";
import ReportConfig from "../../../assets/content-config/report-page.json"
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'mb-capital-report',
  templateUrl: './capital-report.component.html'
})
export class CapitalReportComponent{
    pageForm : FormGroup = new FormGroup({
        currentPage: new FormControl(0),
        pageSize: new FormControl(8),
        sort: new FormControl("amount"),
        sortOrder: new FormControl("desc")
    })

    $entries: Observable<Page<CapitalEntry>> =this.capitalService.getCapitalEntriesPage(this.pageForm.getRawValue());
    $ordersWithDateRange: Observable<Page<CapitalEntry>> = merge(this.topService.dateRangeFrom.statusChanges,this.pageForm.statusChanges).pipe(
        mergeMap(() => this.capitalService.getCapitalEntriesPage(this.pageForm.getRawValue()))
    );

    capitalHeads: string[] = ReportConfig.capital.heads;
    constructor(protected topService:TopNavService,protected capitalService:CapitalService) {
    }
}
