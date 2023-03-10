package bi.manager.web.controllers;

import bi.manager.facade.data.MBCapitalData;
import bi.manager.facade.data.MBCapitalEntryData;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.facades.MBCapitalFacade;
import bi.manager.web.ManagerWebConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

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
    public Collection<MBCapitalEntryData> getEntries(@RequestBody MBDateRangeData range , @PathVariable String code){
        return facade.getCapitalEntries(code,range.getFrom(),range.getTo());
    }

    @GetMapping
    public MBCapitalData getCapital(@PathVariable String code){
        return facade.getCapitalByFacility(code);
    }
}
