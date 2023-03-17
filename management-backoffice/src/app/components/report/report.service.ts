import {Observable} from "rxjs";
import {ClientReport, DateRange} from "../../model/navigation.model";

export abstract class ReportService{
    abstract getClientReport(range?:DateRange): Observable<ClientReport>;
}