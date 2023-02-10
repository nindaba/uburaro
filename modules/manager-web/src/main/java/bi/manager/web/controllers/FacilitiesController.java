package bi.manager.web.controllers;

import bi.manager.web.ManagerWebConstants;
import bi.manager.facade.data.MBFacilityData;
import bi.manager.facade.facades.MBFacilityFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

import static bi.manager.web.ManagerWebConstants.Controller.Facility.endpoint;

@RestController
@CrossOrigin()
public class FacilitiesController {
    @Resource(name = "facilityFacade")
    protected MBFacilityFacade facilityFacade;

    @RequestMapping(path = endpoint, method = RequestMethod.GET)
    public Collection<MBFacilityData> getFacilities(
            @RequestParam(name = ManagerWebConstants.Controller.allFields, required = false) boolean allFields) {

        return facilityFacade.getAllFacilities(allFields);
    }

    @RequestMapping(path = ManagerWebConstants.Controller.Facility.facility, method = RequestMethod.GET)
    public MBFacilityData getFacilityForCode(
            @RequestParam(name = ManagerWebConstants.Controller.allFields, required = false) boolean allFields,
            @PathVariable String code) {

        return facilityFacade.getFacilityByCode(code, allFields);
    }

    @RequestMapping(path = ManagerWebConstants.Controller.Facility.facility, method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public void updateFacility(@PathVariable String code, @RequestBody MBFacilityData facilityData) {
        facilityFacade.updateFacility(code, facilityData);
    }

    @RequestMapping(path = endpoint, method = RequestMethod.DELETE)
    public void deleteFacility(@RequestParam Set<String> codes) {
        facilityFacade.deleteFacilities(codes);
    }

    @RequestMapping(path = endpoint , method = RequestMethod.POST)
    public void createFacility(@RequestBody MBFacilityData facilityData){
        facilityFacade.createFacility(facilityData);
    }
}
