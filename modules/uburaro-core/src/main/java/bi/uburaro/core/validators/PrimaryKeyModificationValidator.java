package bi.uburaro.core.validators;

import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.PrimaryKeyType;

import java.util.Map;

public class PrimaryKeyModificationValidator<TYPE extends ItemType> implements Validator<TYPE> {
    private final TypeService typeService;

    public PrimaryKeyModificationValidator(TypeService typeService) {
        this.typeService = typeService;
    }


    @Override
    public void validate(TYPE item, Map<String, String> errors) {

        HotelType itemByCode = typeService.findItemByCode(item.getCode(), HotelType.class);

        if (itemByCode != null &&
                !item.getPrimaryKey().equals(itemByCode.getPrimaryKey())) {

            errors.put(PrimaryKeyType.PRIMARY_KEY, "The {} is modified while it is read only");
        }

    }

    @Override
    public boolean isSupported(Class<?> typeClass) {
        return typeClass.isAssignableFrom(ItemType.class);
    }
}
