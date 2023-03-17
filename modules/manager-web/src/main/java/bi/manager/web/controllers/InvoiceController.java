package bi.manager.web.controllers;

import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.MBInvoiceData;
import bi.manager.facade.data.NamedItemData;
import bi.manager.facade.facades.MBClientFacade;
import bi.manager.facade.facades.MBInvoiceFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Collection;
import java.util.Set;

import static bi.manager.web.ManagerWebConstants.Controller.Client.Invoice.*;

@RestController
@CrossOrigin()
public class InvoiceController {
    @Resource(name = "mBInvoiceFacade")
    protected MBInvoiceFacade invoiceFacade;
    @Resource(name = "mBClientFacade")
    protected MBClientFacade clientFacade;

    @GetMapping(value = facilityInvoices)
    Collection<MBInvoiceData> getInvoicesByFacilityCode(@PathVariable String code){
        return invoiceFacade.getAllInvoicesByFacilityCode(code);
    }

    @GetMapping(value = clientInvoices)
    Collection<MBInvoiceData> getAllInvoicesByClientCode(@PathVariable String clientCode){
        return invoiceFacade.getAllInvoicesByClientCode(clientCode);
    }
    @PostMapping(value =facilityInvoices)
    Collection<MBInvoiceData> getInvoiceReport(@RequestBody MBDateRangeData rangeData,@PathVariable String code){
        return invoiceFacade.getInvoiceReport(code,rangeData);
    }

    @PatchMapping(value = clientInvoices)
    void updateInvoice(@RequestBody MBInvoiceData invoice,@PathVariable String clientCode){
        NamedItemData named = new NamedItemData();
        named.setCode(clientCode);
        invoice.setClient(named);
        invoiceFacade.updateInvoice(invoice);
    }

    @DeleteMapping(value = clientInvoices)
    void deleteInvoice(@RequestParam("codes") Set<String> invoiceNumbers){
        invoiceFacade.deleteInvoice(invoiceNumbers);
    }
}
