package bi.manager.web.controllers;

import bi.manager.core.types.enums.MBPaymentModeEnum;
import bi.manager.facade.data.*;
import bi.manager.facade.data.jasper.MBClientJRData;
import bi.manager.facade.facades.MBClientFacade;
import bi.manager.facade.facades.MBPdfReportFacade;
import bi.manager.facade.facades.MBRentContractFacade;
import bi.manager.web.ManagerWebConstants;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import static bi.manager.web.ManagerWebConstants.Controller.Client.client;
import static bi.manager.web.ManagerWebConstants.Controller.Client.rentContract;
import static bi.manager.web.ManagerWebConstants.Controller.report;

@RestController
@RequestMapping(value = ManagerWebConstants.Controller.Client.endpoint)
@CrossOrigin()
public class ClientController {
    @Resource(name = "mBClientFacade")
    protected MBClientFacade facade;
    @Resource(name = "mBRentContractFacade")
    protected MBRentContractFacade rentContractFacade;
    @Resource(name = "clientPdfReportFacade")
    protected MBPdfReportFacade pdfReportFacade;

    @GetMapping
    public Collection<MBClientData> getClientsByFacility(@PathVariable String code, @RequestParam(required = false) boolean allFields) {
        return facade.getClientsByFacilityCode(code, allFields);
    }

    @PostMapping(value = report)
    public MBClientReportData getInvoiceReport(@RequestBody MBDateRangeData rangeData, @PathVariable String code) {
        return facade.getClientsReport(code, rangeData);
    }

    @GetMapping(value = client)
    public MBClientData getClient(@PathVariable String code, @RequestParam(required = false) boolean allFields, @PathVariable String clientCode) {
        return facade.getClientByCode(clientCode, allFields);
    }

    @GetMapping(value = rentContract)
    public Collection<MBRentContractData> getRentContracts(@PathVariable String clientCode, @PathVariable String code) {
        return rentContractFacade.getContractsByClientCode(clientCode);
    }

    @DeleteMapping
    public void deleteCategories(@RequestParam Set<String> codes) {
        facade.deleteClients(codes);
    }

    @PatchMapping
    public void updateClient(@RequestBody MBClientData client, @PathVariable String code) {
        MBFacilityData facility = new MBFacilityData();
        facility.setCode(code);
        client.setFacility(facility);
        facade.updateClient(client);
    }

    @DeleteMapping(value = ManagerWebConstants.Controller.Orders.clientOrders)
    public void deleteOrders(@RequestParam(name = "codes") Set<String> orderNumbers, @PathVariable String clientCode, @PathVariable String code) {
        facade.deleteOrders(orderNumbers);
    }

    @GetMapping(value = "/pdf")
    public void getPdfReport(@PathVariable String code,
                             @RequestParam Date from,
                             @RequestParam Date to,
                             HttpServletResponse response) throws IOException {

        final MBDateRangeData range = new MBDateRangeData();
        final MBClientJRData report = new MBClientJRData();

        range.setFrom(from);
        range.setTo(to);

        final MBClientReportData clientsReport = facade.getClientsReport(code, range);

        final Collection<MBInvoiceData> invoices = clientsReport.getInvoicesPage().getContent().stream()
                .collect(Collectors.filtering(invoice -> invoice.getPaymentMode().equals(MBPaymentModeEnum.DEBT)
                        , Collectors.toList()));

        report.setRange(range);
        report.setInvoices(invoices);
        report.setClients(clientsReport.getClients());

        pdfReportFacade.getPdfReport(report, response.getOutputStream());
    }
}
