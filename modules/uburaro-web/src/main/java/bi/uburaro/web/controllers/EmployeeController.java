package bi.uburaro.web.controllers;

import bi.uburaro.facade.data.EmployeeData;
import bi.uburaro.facade.facades.EmployeeFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Collection;
import java.util.List;

import static bi.uburaro.web.UburaroWebConstants.Controller.Employee.*;
import static bi.uburaro.web.UburaroWebConstants.Controller.EmployeeGroup;
import static bi.uburaro.web.UburaroWebConstants.Controller.Hotel.hotelCode;
import static bi.uburaro.web.UburaroWebConstants.Controller;

@RestController
public class EmployeeController {
    @Resource(name = "employeeFacade")
    protected EmployeeFacade employeeFacade;

    @RequestMapping(path = endpoint, method = RequestMethod.GET)
    public Collection<EmployeeData> getAllEmployees(@RequestParam(name = Controller.allFields, required = false) boolean allFields) {
        return employeeFacade.getAllEmployees(allFields);
    }

    @RequestMapping(path = hotelEmployees, method = RequestMethod.GET)
    public Collection<EmployeeData> getHotelEmployees(@RequestParam(name = Controller.allFields, required = false) boolean allFields,
                                                      @PathVariable(hotelCode) String code) {
        return employeeFacade.getEmployeesForHotel(code, allFields);
    }

    @RequestMapping(path = employee, method = RequestMethod.GET)
    public EmployeeData getEmployeeForCode(@RequestParam(name = Controller.allFields, required = false) boolean allFields, @PathVariable(employeeCode) String code) {
        return employeeFacade.getEmployeeForCode(code, allFields);
    }

    @RequestMapping(path =  employee, method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public void updateToGroup(@PathVariable(employeeCode) String code,@RequestParam(name = EmployeeGroup.groupCodes) List<String> groupCodes){
        employeeFacade.updateGroups(code,groupCodes);
    }

    @RequestMapping(path = employee, method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable String employeeCode){
        employeeFacade.removeEmployee(employeeCode);
    }

}
