package bi.manager.web.controllers;

import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.total.MBCapitalSummaryData;
import bi.manager.facade.facades.MBTotalSummaryFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static bi.manager.web.ManagerWebConstants.Controller.Summary.capital;
import static bi.manager.web.ManagerWebConstants.Controller.Summary.endpoint;

@RestController
@RequestMapping(value = endpoint)
@CrossOrigin
public class TotalController {
    @Resource(name = "mBTotalSummaryFacade")
    protected MBTotalSummaryFacade totalSummaryFacade;
    @PostMapping(capital)
    public MBCapitalSummaryData getTotalCapitalSummary(@RequestBody MBDateRangeData range ,
                                                       @PathVariable String code){
        return totalSummaryFacade.getCapitalSummary(code,range);
    }
}
