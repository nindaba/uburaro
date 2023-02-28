package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBInvoiceService;
import bi.manager.facade.converters.client.InvoiceMapper;
import bi.manager.facade.data.MBInvoiceData;
import bi.manager.facade.facades.MBInvoiceFacade;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service(value = "mBInvoiceFacade")
public class DefaultMBInvoiceFacade implements MBInvoiceFacade {

    protected final MBInvoiceService invoiceService;
    protected final InvoiceMapper mapper;

    public DefaultMBInvoiceFacade(MBInvoiceService invoiceService, InvoiceMapper mapper) {
        this.invoiceService = invoiceService;
        this.mapper = mapper;
    }

    @Override
    public Collection<MBInvoiceData> getAllInvoicesByFacilityCode(String facility) {
        return mapper.invoicesToData(invoiceService.getAllInvoicesByFacilityCode(facility));
    }

    @Override
    public Collection<MBInvoiceData> getAllInvoicesByClientCode(String client) {
        return mapper.invoicesToData(invoiceService.getAllInvoicesByClientCode(client));
    }

    @Override
    public void updateInvoice(MBInvoiceData invoice) {
        invoiceService.updateInvoice(mapper.invoiceToType(invoice));
    }

    @Override
    public void deleteInvoice(Set<String> invoiceNumbers) {
        invoiceService.deleteInvoice(invoiceNumbers);
    }
}
