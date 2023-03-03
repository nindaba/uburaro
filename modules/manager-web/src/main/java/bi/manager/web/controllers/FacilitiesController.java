package bi.manager.web.controllers;

import bi.manager.facade.data.MBFacilityData;
import bi.manager.facade.data.MBRentContractData;
import bi.manager.facade.facades.MBFacilityFacade;
import bi.manager.facade.facades.MBRentContractFacade;
import bi.manager.web.ManagerWebConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

import static bi.manager.web.ManagerWebConstants.Controller.Facility.endpoint;
import static bi.manager.web.ManagerWebConstants.Controller.Facility.facilityCode;

@RestController
@CrossOrigin()
@RequestMapping(path = endpoint)
public class FacilitiesController {
    @Resource(name = "facilityFacade")
    protected MBFacilityFacade facilityFacade;
    @Resource(name = "mBRentContractFacade")
    protected MBRentContractFacade rentContractFacade;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<MBFacilityData> getFacilities(
            @RequestParam(name = ManagerWebConstants.Controller.allFields, required = false) boolean allFields) {

        return facilityFacade.getAllFacilities(allFields);
    }

    @RequestMapping(path = facilityCode, method = RequestMethod.GET)
    public MBFacilityData getFacilityForCode(
            @RequestParam(name = ManagerWebConstants.Controller.allFields, required = false) boolean allFields,
            @PathVariable String code) {

        return facilityFacade.getFacilityByCode(code, allFields);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public void updateFacility(@RequestBody MBFacilityData facilityData) {
        facilityFacade.updateFacility(facilityData);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteFacility(@RequestParam Set<String> codes) {
        facilityFacade.deleteFacilities(codes);
    }

    @PostMapping
    public Collection<MBRentContractData> onBackOfficeInit(){
        return rentContractFacade.generateOrders();
    }
}
