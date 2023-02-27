package bi.manager.web.controllers;

import bi.manager.facade.data.MBRentContractData;
import bi.manager.facade.data.MBRentPropertyData;
import bi.manager.facade.data.MBFacilityData;
import bi.manager.facade.facades.MBRentContractFacade;
import bi.manager.facade.facades.MBRentPropertyFacade;
import bi.manager.web.ManagerWebConstants;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

import static bi.manager.web.ManagerWebConstants.Controller.Rent.rent;
import static bi.manager.web.ManagerWebConstants.Controller.Rent.rentContract;

@RestController
@RequestMapping(value = ManagerWebConstants.Controller.Rent.endpoint)
@CrossOrigin()
public class RentPropertyController {
    @Resource(name = "mBRentPropertyFacade")
    protected MBRentPropertyFacade facade;
    @Resource(name = "mBRentContractFacade")
    protected MBRentContractFacade rentContractFacade;

    @GetMapping
    public Collection<MBRentPropertyData> getRents(@PathVariable String code, @RequestParam(required = false) boolean allFields) {
        return facade.getRentsByFacilityCode(code, allFields);
    }

    @GetMapping(value = rent)
    public MBRentPropertyData getRent(@PathVariable String code, @RequestParam(required = false) boolean allFields, @PathVariable String rentCode) {
        return facade.getRentalPropertyByCode(rentCode, allFields);
    }

    @GetMapping(value = rentContract)
    public Collection<MBRentContractData> getRentContracts(@PathVariable String code, @PathVariable String rentCode){
        return rentContractFacade.getContractsByRentCode(rentCode);
    }
    @PatchMapping(value = rentContract)
    public void updateContract(@PathVariable String code, @PathVariable(required = false) String rentCode, @RequestBody MBRentContractData contract){
        rentContractFacade.updateContract(contract);
    }

    @DeleteMapping(value = rentContract)
    public void deleteContracts(@PathVariable(required = false) String code, @PathVariable(required = false) String rentCode,@RequestParam Set<String> codes){
        rentContractFacade.deleteContract(codes);
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
        facade.updateRental(rental);
    }
}
