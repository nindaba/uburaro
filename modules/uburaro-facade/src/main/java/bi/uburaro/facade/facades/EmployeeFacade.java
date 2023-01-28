package bi.uburaro.facade.facades;

import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.facade.data.EmployeeData;

import java.util.Collection;
import java.util.List;

public interface EmployeeFacade {
    /**
     * Gets all the employees
     * @param allFields determines if all the attributes will be included
     * @return collection of Employees data
     */
    Collection<EmployeeData> getAllEmployees(boolean allFields);

    /**
     * Gets all the employees for specific hotel
     *
     * @param allFields determines if all the attributes will be included
     * @param code of the hotel
     * @return collection of Employees data
     */
    Collection<EmployeeData> getEmployeesForHotel(String code,boolean allFields);

    /**
     * Gets a specific employee by code
     *
     * @param code
     * @param allFields determines if all the attributes will be included
     * @return employee
     */
    EmployeeData getEmployeeForCode(String code,boolean allFields);

    /**
     * Add the employee to the groups by the code provided if the employee doesn't belong to the group already
     * otherwise the employee will be removed from the group
     *
     * @param code of the employee
     * @param groupCodes of the Employee groups
     */
    void updateGroups(String code, List<String> groupCodes);

    /**
     * Deletes the employee
     *
     * @throws NotFoundException
     * @param code of the employee
     */
    void removeEmployee(String code);

}
