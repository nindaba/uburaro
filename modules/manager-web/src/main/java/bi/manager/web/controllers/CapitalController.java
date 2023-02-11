package bi.manager.web.controllers;

import bi.manager.facade.data.MBCapitalData;
import bi.manager.facade.data.MBCapitalEntryData;
import bi.manager.facade.facades.MBCapitalFacade;
import bi.manager.web.ManagerWebConstants;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping(value = ManagerWebConstants.Controller.Facility.capital)
@CrossOrigin()
public class CapitalController {
    @Resource(name = "mbCapitalFacade")
    protected MBCapitalFacade facade;

    @PostMapping("/{value}/{type}")
    public void addCapital(@PathVariable String code, @PathVariable long value, @PathVariable String type) {
        facade.addCapital(code,value,type);
    }

    @GetMapping("/entries")
    public Collection<MBCapitalEntryData> getEntries(@RequestParam Date from, @RequestParam Date to, @PathVariable String code){
        return facade.getCapitalEntries(code,from,to);
    }

    @GetMapping
    public MBCapitalData getCapital(@PathVariable String code){
        return facade.getCapitalByFacility(code);
    }
}
