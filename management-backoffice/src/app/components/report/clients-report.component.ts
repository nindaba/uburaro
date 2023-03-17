import {Component} from '@angular/core';
import {mergeMap, Observable} from "rxjs";
import {ClientReport} from "../../model/navigation.model";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {ReportService} from "./report.service";
import ReportHeaders from "../../../assets/content-config/report-page.json"

@Component({
  selector: 'mb-clients-report',
  templateUrl: './clients-report.component.html'
})
export class ClientsReportComponent{
  $clientReport: Observable<ClientReport> = this.reportService.getClientReport();
  $clientsWithNewRange: Observable<ClientReport> = this.topService.dateRangeFrom.valueChanges.pipe(mergeMap( ()=> this.reportService.getClientReport()));

  clientHeader:string[] = ReportHeaders.clients.client;
  invoiceHeader:string[] = ReportHeaders.clients.invoice;

  constructor(protected topService: TopNavService,protected reportService: ReportService) {
  }
}
