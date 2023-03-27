import {Observable} from "rxjs";
import {
    CapitalSummary,
    ClientReport,
    DateRange,
    InventoryOrder, InventoryOrderType,
    Page,
    Pageable,
    RentContract,
    RentOrder
} from "../../model/navigation.model";

export abstract class ReportService{
    abstract getClientReport(pageable: Pageable,range?:DateRange): Observable<ClientReport>;

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

    /**
     * Gets facility paged inventory orders within the selected range
     *
     * @param pageable
     *
     */
    abstract getFacilityInventoryOrdersByRange(pageable:Pageable,orderEntry:InventoryOrderType): Observable<Page<InventoryOrder>>;

    /**
     * Gets the capital summary for the selected facility in the give date range <br>
     *
     * @return an observable of Capital summary
     */
    abstract getCapitalSummary(): Observable<CapitalSummary>;
}