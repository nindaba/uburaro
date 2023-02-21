package bi.manager.web.controllers;

import bi.manager.facade.data.MBRentPropertyData;
import bi.manager.facade.data.MBFacilityData;
import bi.manager.facade.facades.MBRentPropertyFacade;
import bi.manager.web.ManagerWebConstants;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

import static bi.manager.web.ManagerWebConstants.Controller.Rent.rent;

@RestController
@RequestMapping(value = ManagerWebConstants.Controller.Rent.endpoint)
@CrossOrigin()
public class RentPropertyController {
    @Resource(name = "mBRentPropertyFacade")
    protected MBRentPropertyFacade facade;

    @GetMapping
    public Collection<MBRentPropertyData> getRents(@PathVariable String code, @RequestParam(required = false) boolean allFields) {
        return facade.getRentsByFacilityCode(code, allFields);
    }

    @GetMapping(value = rent)
    public MBRentPropertyData getRent(@PathVariable String code, @RequestParam(required = false) boolean allFields, @PathVariable String rentCode) {
        return facade.getRentalPropertyByCode(rentCode, allFields);
    }

    @DeleteMapping
    public void deleteCategories(@RequestParam Set<String> codes) {
        facade.deleteRentals(codes);
    }
    @PatchMapping
    public void updateRental(@RequestBody MBRentPropertyData rental, @PathVariable String code){
        MBFacilityData facility = new MBFacilityData();
        facility.setCode(code);
        rental.setFacility(facility);
        facade.updateClient(rental);
    }
}
