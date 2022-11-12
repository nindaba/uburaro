package bi.uburaro.initialdata.mappers.impl;

import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.AddressType;
import bi.uburaro.core.types.CompanyType;
import bi.uburaro.core.types.CustomerType;
import bi.uburaro.core.types.groups.BranchGroupType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static bi.uburaro.core.types.CustomerType.*;

public class CustomerTypeMapper extends AbstractTypeMapper<CustomerType> {


    protected CustomerTypeMapper(TypeService typeService) {
        super(typeService);
    }

    @Override
    public Class<CustomerType> getTargetClass() {
        return CustomerType.class;
    }

    @Override
    public Map<String, Consumer<String>> createFieldsMapper(final CustomerType target) {
        final Map<String, Consumer<String>> fieldsMapper = new HashMap<>();

        fieldsMapper.putAll(Map.of(
                CODE, target::setCode,
                ACTIVE, value -> target.setActive(Boolean.valueOf(value)),
                VISIBLE, value -> target.setVisible(Boolean.valueOf(value)),
                FIRST_NAME, target::setFirstName,
                LAST_NAME, target::setLastName,
                PHONE, target::setPhone,
                IDENTITY, target::setIdentity,
                NATIONALITY, target::setNationality,
                GENDER, target::setGender,
                AGE, target::setAge));

        fieldsMapper.putAll(Map.of(
                ADDRESS, addressCodes -> getStringStream(addressCodes)
                        .map(code -> typeService.findItemByCode(code, AddressType.class))
                        .forEach(target.getAddress()::add),

                COMPANIES, companyCodes -> getStringStream(companyCodes)
                        .map(code -> typeService.findItemByCode(code, CompanyType.class))
                        .forEach(target.getCompanies()::add),

                BRANCH_GROUPS, branches -> getStringStream(branches)
                        .map(code -> typeService.findItemByCode(code, BranchGroupType.class))
                        .forEach(target.getBranchGroups()::add)));

        return fieldsMapper;
    }
}
