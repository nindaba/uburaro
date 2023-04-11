package bi.manager.web.controllers;

import bi.manager.core.types.client.MBOrderType;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.MBPageData;
import bi.manager.facade.data.MBRentContractData;
import bi.manager.facade.data.MBRentOrderData;
import bi.manager.facade.data.jasper.MBRentJRData;
import bi.manager.facade.facades.MBPdfReportFacade;
import bi.manager.facade.facades.MBRentContractFacade;
import bi.manager.facade.facades.MBRentOrderFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static bi.manager.facade.factories.PageFactory.createPage;
import static bi.manager.web.ManagerWebConstants.Controller.Orders.Rent.*;

@RestController
@RequestMapping(value = endpoint)
@CrossOrigin()
public class RentOrderController {
    @Resource(name = "mBRentOrderFacade")
    protected MBRentOrderFacade facade;
    @Resource(name = "rentPdfReportFacade")
    protected MBPdfReportFacade pdfReportFacade;
    @Resource(name = "mBRentContractFacade")
    protected MBRentContractFacade rentContractFacade;

    @GetMapping(value = facilityOrders)
    Collection<MBRentOrderData> getOrderByFacilityCode(@PathVariable String code) {
        return facade.getOrderByFacilityCode(code);
    }

    @PostMapping(value = facilityOrders)
    MBPageData<MBRentOrderData> getOrderByFacilityCode(@PathVariable String code,
                                                       @RequestBody MBDateRangeData range,
                                                       @RequestParam(required = false, defaultValue = "100") int pageSize,
                                                       @RequestParam(required = false, defaultValue = "0") int currentPage,
                                                       @RequestParam(required = false, defaultValue = MBOrderType.ORDER_DATE) String sort,
                                                       @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        return facade.getOrderByFacilityCode(code, range, createPage(pageSize, currentPage, sort, sortOrder));
    }

    @GetMapping(value = facilityOrdersPdf)
    void getOrderByFacilityCode(@PathVariable String code,
                                @RequestParam Date from,
                                @RequestParam Date to,
                                HttpServletResponse response) throws IOException {

        final MBDateRangeData range = new MBDateRangeData();
        range.setFrom(from);
        range.setTo(to);

        final Collection<MBRentOrderData> rentOrders = facade.getOrderByFacilityCode(code, range);
        final Collection<MBRentContractData> contracts = rentContractFacade.getFacilityContracts(code, range);
        final MBRentJRData report = new MBRentJRData();

        report.setOrders(rentOrders);
        report.setRange(range);
        report.setContracts(contracts);
        pdfReportFacade.getPdfReport(report, response.getOutputStream());
    }

    @GetMapping(value = inventoryOrders)
    Collection<MBRentOrderData> getOrderByRentCode(@PathVariable String code) {
        return facade.getOrderByRentCode(code);
    }

    @GetMapping(value = clientOrders)
    Collection<MBRentOrderData> getOrderByClientCode(@PathVariable String code) {
        return facade.getOrderByClientCode(code);
    }

    @GetMapping(value = contractOrders)
    Collection<MBRentOrderData> getOrderByContract(@PathVariable String code) {
        return facade.getOrdersByContract(code);
    }

    @PostMapping
    void placeOrder(@RequestBody MBRentOrderData order) {
        facade.placeOrder(order);
    }

    @DeleteMapping
    void deleteOrder(@RequestParam Set<String> orderNumbers) {
        facade.deleteOrder(orderNumbers);
    }
}
