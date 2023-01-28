package bi.uburaro.initialdata.mappers.impl;

import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.EmployeeType;
import bi.uburaro.core.types.groups.EmployeeGroupType;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Consumer;

import static bi.uburaro.core.types.groups.EmployeeGroupType.*;

public class EmployeeGroupTypeMapper extends AbstractTypeMapper<EmployeeGroupType> {

    public EmployeeGroupTypeMapper(TypeService typeService) {
        super(typeService);
    }

    @Override
    public Class<EmployeeGroupType> getTargetClass() {
        return EmployeeGroupType.class;
    }

    @Override
    public Map<String, Consumer<String>> createFieldsMapper(final EmployeeGroupType target) {
        final Map<String, Consumer<String>> fieldsMapper = new HashMap<>();
        fieldsMapper.putAll(Map.of(
                CODE, target::setCode,
                ACTIVE, value -> target.setActive(Boolean.valueOf(value)),
                VISIBLE, value -> target.setVisible(Boolean.valueOf(value)),
                GROUP_NAME, target::setGroupName));

        fieldsMapper.putAll(Map.of(
                EMPLOYEES, employeesCodes -> getStringStream(employeesCodes)
                        .map(code -> typeService.findItemByCode(code, EmployeeType.class))
                        .forEach(employee -> addEmployee(target, employee))));

        return fieldsMapper;
    }

    private void addEmployee(EmployeeGroupType target, EmployeeType employee) {
        if (CollectionUtils.isEmpty(target.getEmployees())){
            target.setEmployees(new HashSet<>());
        }
        target.getEmployees().add(employee);
    }
}
