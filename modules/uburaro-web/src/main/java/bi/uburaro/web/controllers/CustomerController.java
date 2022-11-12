package bi.uburaro.web.controllers;

import bi.uburaro.facade.data.CustomerData;
import bi.uburaro.facade.facades.CustomerFacade;
import bi.uburaro.web.UburaroWebConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static bi.uburaro.web.UburaroWebConstants.Controller.Company;
import static bi.uburaro.web.UburaroWebConstants.Controller.Customer.*;
import static bi.uburaro.web.UburaroWebConstants.Controller;

@RestController
public class CustomerController {
    @Resource(name = "customerFacade")
    protected CustomerFacade customerFacade;

    @RequestMapping(path = endpoint,method = RequestMethod.GET)
    public Collection<CustomerData> getAllCustomers(@RequestParam(name = Controller.allFields,required = false) boolean allFields){
        return customerFacade.getAllCustomers(allFields);
    }

    @RequestMapping(path = hotelCustomers,method = RequestMethod.GET)
    public Collection<CustomerData> getHotelCustomers(@RequestParam(name =  Controller.allFields,required = false) boolean allFields, @PathVariable String hotelCode){
        return customerFacade.getHotelCustomers(hotelCode, allFields);
    }

    @RequestMapping(path = customer,method = RequestMethod.GET)
    public CustomerData getCustomer(@RequestParam(name =  Controller.allFields,required = false) boolean allFields, @PathVariable String customerCode){
        return customerFacade.getCustomerByCode(customerCode, allFields);
    }

    @RequestMapping(path = customer,method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomerCompanies(@PathVariable String customerCode, @RequestParam(name = Company.companyCodes) List<String> companyCodes){
        customerFacade.updateCompanies(customerCode,companyCodes);
    }

    @RequestMapping(path = customer,method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable String customerCode){
        customerFacade.deleteCustomer(customerCode);
    }
}
