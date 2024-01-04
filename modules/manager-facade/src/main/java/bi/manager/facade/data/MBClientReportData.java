package bi.manager.facade.data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MBClientReportData {
    Set<MBClientData> clients = new HashSet<>();
    MBPageData<MBInvoiceData> invoicesPage = new MBPageData<>();

    public Set<MBClientData> getClients() {
        return clients;
    }

    public MBPageData<MBInvoiceData> getInvoicesPage() {
        return invoicesPage;
    }
}
