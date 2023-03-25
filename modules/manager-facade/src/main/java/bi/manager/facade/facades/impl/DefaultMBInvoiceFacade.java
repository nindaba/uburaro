package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBInvoiceService;
import bi.manager.core.utils.MBPage;
import bi.manager.facade.converters.client.InvoiceMapper;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.MBInvoiceData;
import bi.manager.facade.data.MBPageData;
import bi.manager.facade.data.MBPageableData;
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

    @Override
    public Collection<MBInvoiceData> getInvoiceReport(String facility, MBDateRangeData range) {
        return mapper.invoicesToData(
                invoiceService.getInvoiceReport(facility,range.getFrom(),range.getTo())
        );
    }

    @Override
    public Collection<MBInvoiceData> getInvoiceReport(final String facility, final MBDateRangeData range,final MBPageableData pageable) {
        final MBPageData<MBInvoiceData> pageData = new MBPageData<>();
        invoiceService.getInvoiceReport(facility,range.getFrom(),range.getTo());
        return null;
    }
}
