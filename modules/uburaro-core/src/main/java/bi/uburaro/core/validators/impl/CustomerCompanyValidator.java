package bi.uburaro.core.validators.impl;

import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.CustomerType;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.groups.CompanyGroupType;
import bi.uburaro.core.validators.Validator;

import java.util.Map;

public class CustomerCompanyValidator<TYPE extends ItemType> implements Validator<TYPE> {
    private final TypeService typeService;

    public CustomerCompanyValidator(TypeService typeService) {
        this.typeService = typeService;
    }


    @Override
    public void validate(TYPE item, Map<String, String> errors) {


    }

    @Override
    public boolean isSupported(Class<?> typeClass) {
        return typeClass.isAssignableFrom(CustomerType.class)
                || typeClass.isAssignableFrom(CompanyGroupType.class);
    }
}
