import {Component} from '@angular/core';
import {merge, mergeMap, Observable, tap} from "rxjs";
import {ClientReport, Invoice, Pageable} from "../../model/navigation.model";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {ReportService} from "./report.service";
import ReportHeaders from "../../../assets/content-config/report-page.json"
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'mb-clients-report',
  templateUrl: './clients-report.component.html'
})
export class ClientsReportComponent{
  pageForm : FormGroup = new FormGroup({
    currentPage: new FormControl(0),
    pageSize: new FormControl(8),
    sort: new FormControl("amount"),
    sortOrder: new FormControl("desc")
  })

  $clientReport: Observable<ClientReport> = this.reportService.getClientReport(this.pageForm.getRawValue());
  $clientsWithNewRange: Observable<ClientReport> = merge(this.topService.dateRangeFrom.valueChanges,this.pageForm.valueChanges).pipe(
      mergeMap( ()=> this.reportService.getClientReport(this.pageForm.getRawValue())));

  clientHeader:string[] = ReportHeaders.clients.client;
  invoiceHeader:string[] = ReportHeaders.clients.invoice;

  constructor(protected topService: TopNavService,protected reportService: ReportService) {
  }

  getPaidInvoice(invoices: Invoice[]): Invoice[]{
    return invoices.filter(invoice => invoice.paymentMode != 'DEBT');
  }
}
