package bi.uburaro.core.services.impl;

import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.EmployeeService;
import bi.uburaro.core.services.HotelService;
import bi.uburaro.core.services.SessionService;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.EmployeeType;
import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.groups.BranchGroupType;
import bi.uburaro.core.types.groups.EmployeeGroupType;
import bi.uburaro.core.utils.MessageUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
public class DefaultEmployeeService implements EmployeeService {

    private final TypeService typeService;
    private final SessionService sessionService;
    private final HotelService hotelService;

    public DefaultEmployeeService(TypeService typeService, SessionService sessionService, HotelService hotelService) {
        this.typeService = typeService;
        this.sessionService = sessionService;
        this.hotelService = hotelService;
    }

    @Override
    public Collection<EmployeeType> getAllEmployees() {
        return typeService.findAll(EmployeeType.class);
    }

    @Override
    public Collection<EmployeeType> getHotelEmployees(String code) {
        return Optional.ofNullable(sessionService.getCurrentHotel())
                .or(() -> Optional.ofNullable(hotelService.getHotelByCode(code)))
                .map(HotelType::getBranchGroup).stream()
                .peek(branch ->
                        branch.getPrincipals().removeIf(principal ->
                                StringUtils.equals(principal.getPrimaryKey().getItemType(), EmployeeType.class.getName())))
                .map(branch -> (EmployeeType) branch.getPrincipals())
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeType getEmployeeByCode(String code) {
        return typeService.findItemByCode(code, EmployeeType.class);
    }


    @Override
    public void updateGroups(final String employeeCode, final List<String> groupCodes) {
        final EmployeeType employee = getEmployeeByCode(employeeCode);

        if (employee == null){
            throw new NotFoundException(MessageUtils.format("No Employee of code {} was found", employeeCode));
        }

        employee.getEmployeeGroups().
                removeIf(employeeGroupType -> groupCodes
                        .removeIf(code -> StringUtils.equals(employeeGroupType.getCode(), code)));

        groupCodes.stream()
                .map(code -> typeService.findItemByCode(code, EmployeeGroupType.class))
                .forEach(group -> employee.getEmployeeGroups().add(group));

        typeService.save(employee);
    }
}
