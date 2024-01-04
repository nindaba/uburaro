package bi.manager.web.controllers;

import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.MBInvoiceData;
import bi.manager.facade.data.NamedItemData;
import bi.manager.facade.facades.MBInvoiceFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

import static bi.manager.facade.factories.PageFactory.createPage;
import static bi.manager.web.ManagerWebConstants.Controller.Client.Invoice.clientInvoices;
import static bi.manager.web.ManagerWebConstants.Controller.Client.Invoice.facilityInvoices;

@RestController
@CrossOrigin()
public class InvoiceController {
    @Resource(name = "mBInvoiceFacade")
    protected MBInvoiceFacade invoiceFacade;

    @GetMapping(value = facilityInvoices)
    Collection<MBInvoiceData> getInvoicesByFacilityCode(@PathVariable String code) {
        return invoiceFacade.getAllInvoicesByFacilityCode(code);
    }

    @GetMapping(value = clientInvoices)
    Collection<MBInvoiceData> getAllInvoicesByClientCode(@PathVariable String clientCode) {
        return invoiceFacade.getAllInvoicesByClientCode(clientCode);
    }

    @PostMapping(value = facilityInvoices)
    Collection<MBInvoiceData> getInvoiceReport(@RequestBody MBDateRangeData rangeData,
                                               @PathVariable String code,
                                               @RequestParam(required = false, defaultValue = "100") int pageSize,
                                               @RequestParam(required = false, defaultValue = "0") int currentPage,
                                               @RequestParam(required = false, defaultValue = MBInvoiceType.AMOUNT) String sort,
                                               @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        return invoiceFacade.getInvoiceReport(code, rangeData, createPage(pageSize, currentPage, sort, sortOrder));
    }

    @PatchMapping(value = clientInvoices)
    void updateInvoice(@RequestBody MBInvoiceData invoice, @PathVariable String clientCode) {
        NamedItemData named = new NamedItemData();
        named.setCode(clientCode);
        invoice.setClient(named);
        invoiceFacade.updateInvoice(invoice);
    }

    @DeleteMapping(value = clientInvoices)
    void deleteInvoice(@RequestParam("codes") Set<String> invoiceNumbers) {
        invoiceFacade.deleteInvoice(invoiceNumbers);
    }
}
