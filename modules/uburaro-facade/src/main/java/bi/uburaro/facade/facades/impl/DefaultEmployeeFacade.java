package bi.uburaro.facade.facades.impl;

import bi.uburaro.core.services.EmployeeService;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.EmployeeType;
import bi.uburaro.facade.converters.Converter;
import bi.uburaro.facade.converters.Converters;
import bi.uburaro.facade.data.EmployeeData;
import bi.uburaro.facade.facades.EmployeeFacade;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DefaultEmployeeFacade implements EmployeeFacade {
    protected final Converter<EmployeeType,EmployeeData> converter;
    protected final Converter<EmployeeType,EmployeeData> fullConverter;
    protected final EmployeeService employeeService;
    protected final TypeService typeService;

    public DefaultEmployeeFacade(Converter<EmployeeType, EmployeeData> converter, Converter<EmployeeType, EmployeeData> fullConverter, EmployeeService employeeService, TypeService typeService) {
        this.converter = converter;
        this.fullConverter = fullConverter;
        this.employeeService = employeeService;
        this.typeService = typeService;
    }

    @Override
    public Collection<EmployeeData> getAllEmployees(boolean allFields) {
        return Converters.convertAll(
                allFields ? fullConverter : converter,
                employeeService.getAllEmployees());
    }

    @Override
    public Collection<EmployeeData> getEmployeesForHotel(String code, boolean allFields) {
        return Converters.convertAll(
                allFields ? fullConverter : converter,
                employeeService.getHotelEmployees(code));
    }

    @Override
    public EmployeeData getEmployeeForCode(String code, boolean allFields) {
        return Converters.convert(
                allFields ? fullConverter : converter,
                employeeService.getEmployeeByCode(code));
    }


    @Override
    public void updateGroups(String employeeCode, List<String> groupCodes) {
        employeeService.updateGroups(employeeCode, groupCodes);
    }

    @Override
    public void removeEmployee(String code){
        typeService.delete(code,EmployeeType.class);
    }
}
