package bi.manager.facade.facades;

import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.facade.data.MBInvoiceData;

import java.util.Collection;
import java.util.Set;

public interface MBInvoiceFacade {

    /**
     * Ges all the invoices that belong to the facility clients
     *
     * @param facility
     * @return collection of invoices
     */
    Collection<MBInvoiceData> getAllInvoicesByFacilityCode(String facility);

    /**
     * Gets all the invoices that belong to the client
     *
     * @param client
     * @return collection of invoices
     */
    Collection<MBInvoiceData> getAllInvoicesByClientCode(String client);

    /**
     * Updates or creates an invoice for the client
     *
     * @param invoice
     * @apiNote the invoice must contain a client
     */
    void updateInvoice(MBInvoiceData invoice);

    /**
     * Delete invoices which belong to the invoice Numbers
     */
    void deleteInvoice(Set<String> invoiceNumbers);
}
