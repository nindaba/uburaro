package bi.manager.web.controllers;

import bi.manager.core.types.client.MBRentContractType;
import bi.manager.facade.data.*;
import bi.manager.facade.facades.MBRentContractFacade;
import bi.manager.facade.facades.MBRentPropertyFacade;
import bi.manager.web.ManagerWebConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

import static bi.manager.facade.factories.PageFactory.createPage;
import static bi.manager.web.ManagerWebConstants.Controller.Rent.*;

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
    @PostMapping(value = contracts)
    public MBPageData<MBRentContractData> getContractFacilityInRange(@PathVariable String code,
                                                                     @RequestBody MBDateRangeData range,
                                                                     @RequestParam(required = false, defaultValue = "100") int pageSize,
                                                                     @RequestParam(required = false, defaultValue = "0") int currentPage,
                                                                     @RequestParam(required = false, defaultValue = MBRentContractType.NEXT_ORDER_DATE) String sort,
                                                                     @RequestParam(required = false, defaultValue = "asc") String sortOrder){
        return rentContractFacade.getFacilityContracts(code,range, createPage(pageSize, currentPage, sort, sortOrder));
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
