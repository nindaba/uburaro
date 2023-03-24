import {Observable} from "rxjs";
import {ClientReport, DateRange, Page, Pageable, RentContract, RentOrder} from "../../model/navigation.model";

export abstract class ReportService{
    abstract getClientReport(range?:DateRange): Observable<ClientReport>;

    /**
     * Get rent contracts which belong to the selected facility and date range from report
     *
     */
    abstract getContracts(pageable:Pageable):Observable<Page<RentContract>>;
    /**
     * Get selected Facility Rent orders in the selected range
     *
     * @return paged orders
     */
    abstract getFacilityRentOrdersByRange(pageable:Pageable): Observable<Page<RentOrder>>;

}