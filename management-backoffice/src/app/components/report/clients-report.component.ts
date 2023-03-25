import {Component} from '@angular/core';
import {mergeMap, Observable, tap} from "rxjs";
import {ClientReport, Invoice, Pageable} from "../../model/navigation.model";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {ReportService} from "./report.service";
import ReportHeaders from "../../../assets/content-config/report-page.json"

@Component({
  selector: 'mb-clients-report',
  templateUrl: './clients-report.component.html'
})
export class ClientsReportComponent{
  pageable: Pageable = {currentPage: 0, pageSize: 8, sort: "amount", sortOrder: "desc"};

  $clientReport: Observable<ClientReport> = this.reportService.getClientReport(this.pageable);
  $clientsWithNewRange: Observable<ClientReport> = this.topService.dateRangeFrom.valueChanges.pipe(mergeMap( ()=> this.reportService.getClientReport(this.pageable)));

  clientHeader:string[] = ReportHeaders.clients.client;
  invoiceHeader:string[] = ReportHeaders.clients.invoice;

  constructor(protected topService: TopNavService,protected reportService: ReportService) {
  }

  getPaidInvoice(invoices: Invoice[]): Invoice[]{
    return invoices.filter(invoice => invoice.paymentMode != 'DEBT');
  }
}
