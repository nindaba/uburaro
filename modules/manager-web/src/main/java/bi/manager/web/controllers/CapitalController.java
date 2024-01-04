package bi.manager.web.controllers;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.facade.data.MBCapitalData;
import bi.manager.facade.data.MBCapitalEntryData;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.MBPageData;
import bi.manager.facade.data.jasper.MBCapitalReportData;
import bi.manager.facade.facades.MBCapitalFacade;
import bi.manager.facade.facades.MBPdfReportFacade;
import bi.manager.facade.facades.MBTotalSummaryFacade;
import bi.manager.web.ManagerWebConstants;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import static bi.manager.facade.factories.PageFactory.createPage;

@RestController
@RequestMapping(value = ManagerWebConstants.Controller.Facility.capital)
@CrossOrigin()
public class CapitalController {
    @Resource(name = "mbCapitalFacade")
    protected MBCapitalFacade facade;
    @Resource(name = "capitalPdfReportFacade")
    protected MBPdfReportFacade pdfReportFacade;
    @Resource(name = "mBTotalSummaryFacade")
    protected MBTotalSummaryFacade totalSummaryFacade;

    @PostMapping({"/{value}/{type}/{description}", "/{value}/{type}"})
    public void addCapital(@PathVariable String code, @PathVariable long value, @PathVariable String type, @PathVariable(required = false) String description) {
        facade.addCapital(code, value, type, description);
    }

    @PostMapping("/entries")
    public MBPageData<MBCapitalEntryData> getEntries(
            @RequestBody MBDateRangeData range,
            @PathVariable String code,
            @RequestParam(required = false, defaultValue = "100") int pageSize,
            @RequestParam(required = false, defaultValue = "0") int currentPage,
            @RequestParam(required = false, defaultValue = MBCapitalEntryType.AMOUNT) String sort,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        return facade.getCapitalEntries(code, range.getFrom(), range.getTo(), createPage(pageSize, currentPage, sort, sortOrder));
    }

    @GetMapping("/pdf")
    public void getPdfReport(@PathVariable String code,
                             @RequestParam Date from,
                             @RequestParam Date to,
                             HttpServletResponse response) throws IOException {

        final MBCapitalReportData report = new MBCapitalReportData();
        final MBDateRangeData range = new MBDateRangeData();
        range.setFrom(from);
        range.setTo(to);

        final Collection<MBCapitalEntryData> capitalEntries = facade.getCapitalEntries(code, range.getFrom(), range.getTo());

        report.setRange(range);
        report.setEntries(capitalEntries);
        report.setTotals(totalSummaryFacade.getCapitalSummary(code, range));

        pdfReportFacade.getPdfReport(report, response.getOutputStream());
    }

    @GetMapping
    public MBCapitalData getCapital(@PathVariable String code) {
        return facade.getCapitalByFacility(code);
    }
}
