package bi.uburaro.core.services;

import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.types.EmployeeType;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {
    /**
     * Gets all the employees
     *
     * @return Collection of Employees
     */
    Collection<EmployeeType> getAllEmployees();

    /**
     * Gets all employees who belong to the hotel represented by the code of the hotel
     *
     * @param code of the hotel
     * @return Collection of employees
     */
    Collection<EmployeeType> getHotelEmployees(String code);

    /**
     * Gets the employee by the code or the employeeUid
     *
     * @param code of the employee
     * @return Employee
     */
    EmployeeType getEmployeeByCode(String code);

    /**
     * removes or add  employee from the groups,
     * where it checks if the group is existing and removes it from the employee
     * otherwise it adds the employee to the group
     *
     * @param employeeCode of the employee
     * @param groupCodes of the group to be added or removed
     */
    void updateGroups(String employeeCode, List<String> groupCodes);

}
