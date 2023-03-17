package bi.manager.core.services;

import bi.manager.core.types.client.MBInvoiceType;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

public interface MBInvoiceService {

    /**
     * Finds all the invoices that belong to the facility clients
     *
     * @param facility
     * @return collection of invoices
     */
    Collection<MBInvoiceType> getAllInvoicesByFacilityCode(String facility);

    /**
     * Finds all the invoices that belong to the client
     *
     * @param client
     * @return collection of invoices
     */
    Collection<MBInvoiceType> getAllInvoicesByClientCode(String client);

    /**
     * Updates or creates an invoice for the client
     * if the invoice was already existing, we should remove its previous amount from the capital
     * and register it as new but the invoice number does not change
     *
     * if the invoice is new we just add to the capital and remove debt from the client
     *
     * @param invoice
     */
    void updateInvoice(MBInvoiceType invoice);

    /**
     * Delete invoices which belong to the invoice Numbers
     * by removing the capital that they have added and putting back the debt to the client
     * finally deleting the invoice
     */
    void deleteInvoice(Set<String> invoiceNumbers);

    /**
     * Gets all the invoices that belong to ht facility which where places in the date range
     *
     * @param facility
     * @param from
     * @param to
     * @return collection of Invoices
     */
    Collection<MBInvoiceType> getInvoiceReport(String facility, Date from, Date to);
}
