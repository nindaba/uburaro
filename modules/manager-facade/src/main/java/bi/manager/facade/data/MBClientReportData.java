package bi.manager.facade.data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MBClientReportData {
    Set<MBClientData> clients = new HashSet<>();
    Set<MBInvoiceData> invoices = new HashSet<>();

    public Set<MBClientData> getClients() {
        return clients;
    }

    public Set<MBInvoiceData> getInvoices() {
        return invoices;
    }
}
