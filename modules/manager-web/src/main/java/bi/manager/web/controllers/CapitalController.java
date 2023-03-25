package bi.manager.web.controllers;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.client.MBOrderType;
import bi.manager.facade.data.MBCapitalData;
import bi.manager.facade.data.MBCapitalEntryData;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.MBPageData;
import bi.manager.facade.facades.MBCapitalFacade;
import bi.manager.web.ManagerWebConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import static bi.manager.facade.factories.PageFactory.createPage;

@RestController
@RequestMapping(value = ManagerWebConstants.Controller.Facility.capital)
@CrossOrigin()
public class CapitalController {
    @Resource(name = "mbCapitalFacade")
    protected MBCapitalFacade facade;

    @PostMapping({"/{value}/{type}/{description}", "/{value}/{type}"})
    public void addCapital(@PathVariable String code, @PathVariable long value, @PathVariable String type, @PathVariable(required = false) String description) {
        facade.addCapital(code,value,type,description);
    }
    @PostMapping("/entries")
    public MBPageData<MBCapitalEntryData> getEntries(
            @RequestBody MBDateRangeData range ,
            @PathVariable String code,
            @RequestParam(required = false, defaultValue = "100") int pageSize,
            @RequestParam(required = false, defaultValue = "0") int currentPage,
            @RequestParam(required = false, defaultValue = MBCapitalEntryType.AMOUNT) String sort,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder){
        return facade.getCapitalEntries(code,range.getFrom(),range.getTo(),createPage(pageSize,currentPage,sort,sortOrder));
    }
    @GetMapping
    public MBCapitalData getCapital(@PathVariable String code){
        return facade.getCapitalByFacility(code);
    }
}
