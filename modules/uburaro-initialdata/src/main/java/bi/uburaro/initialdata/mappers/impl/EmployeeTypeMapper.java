package bi.uburaro.initialdata.mappers.impl;

import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.EmployeeType;
import bi.uburaro.core.types.groups.BranchGroupType;
import bi.uburaro.core.types.groups.EmployeeGroupType;
import bi.uburaro.initialdata.event.PasswordChangedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static bi.uburaro.core.types.EmployeeType.*;

public class EmployeeTypeMapper extends AbstractTypeMapper<EmployeeType> {
    private final ApplicationEventPublisher applicationEventPublisher;
    public EmployeeTypeMapper(TypeService typeService, ApplicationEventPublisher applicationEventPublisher) {
        super(typeService);
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Class<EmployeeType> getTargetClass() {
        return EmployeeType.class;
    }

    @Override
    public Map<String, Consumer<String>> createFieldsMapper(final EmployeeType target) {
        final Map<String, Consumer<String>> fieldsMapper = new HashMap<>();
        fieldsMapper.putAll(Map.of(
                CODE, target::setCode,
                ACTIVE, value -> target.setActive(Boolean.valueOf(value)),
                VISIBLE, value -> target.setVisible(Boolean.valueOf(value)),
                USERNAME, target::setUsername,
                FIRST_NAME, target::setFirstName,
                LAST_NAME, target::setLastName));

        fieldsMapper.putAll(Map.of(
                EMPLOYEE_GROUPS, addressCodes -> getStringStream(addressCodes)
                        .map(code -> typeService.findItemByCode(code, EmployeeGroupType.class))
                        .forEach(target.getEmployeeGroups()::add),
                BRANCH_GROUPS, branches -> getStringStream(branches)
                        .map(code -> typeService.findItemByCode(code, BranchGroupType.class))
                        .forEach(target.getBranchGroups()::add),
                PASSWORD, value -> {
                    target.setPassword(value);
                    applicationEventPublisher.publishEvent(new PasswordChangedEvent(this, target));
                }));

        return fieldsMapper;
    }
}
