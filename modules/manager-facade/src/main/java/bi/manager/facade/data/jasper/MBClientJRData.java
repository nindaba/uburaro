package bi.manager.facade.data.jasper;

import bi.manager.facade.data.MBClientData;
import bi.manager.facade.data.MBInvoiceData;

import java.util.Collection;
import java.util.Set;

public class MBClientJRData extends MBReportData {

    private Collection<MBInvoiceData> invoices;
    private Collection<MBClientData> clients;

    public void setInvoices(Collection<MBInvoiceData> invoices) {
        this.invoices = invoices;
    }

    public Collection<MBInvoiceData> getInvoices() {
        return invoices;
    }

    public void setClients(Set<MBClientData> clients) {
        this.clients = clients;
    }

    public Collection<MBClientData> getClients() {
        return clients;
    }
}
