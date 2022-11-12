package bi.uburaro.initialdata.mappers.impl;

import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.*;
import bi.uburaro.core.types.groups.BranchGroupType;
import bi.uburaro.core.types.groups.TaxGroupType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static bi.uburaro.core.types.HotelType.*;

public class HotelTypeMapper extends AbstractTypeMapper<HotelType> {

    public HotelTypeMapper(TypeService typeService) {
        super(typeService);
    }

    @Override
    public Class<HotelType> getTargetClass() {
        return HotelType.class;
    }

    @Override
    public Map<String, Consumer<String>> createFieldsMapper(final HotelType target) {
        Map<String, Consumer<String>> fieldsMapper = new HashMap<>();

        fieldsMapper.putAll(Map.of(
                CODE, target::setCode,
                ACTIVE, value -> target.setActive(Boolean.valueOf(value)),
                VISIBLE, value -> target.setVisible(Boolean.valueOf(value)),
                NAME, target::setName,
                ALIAS, target::setAlias,
                DEFAULT_LANGUAGE, value -> target.setDefaultLanguage(typeService.findItemByCode(value, LanguageType.class)),
                DEFAULT_TAX_GROUP, value -> target.setDefaultTaxGroup(typeService.findItemByCode(value, TaxGroupType.class)),
                BRANCH_GROUP, value -> target.setBranchGroup(typeService.findItemByCode(value, BranchGroupType.class))
        ));

        fieldsMapper.putAll(Map.of(
                LANGUAGES, languages -> getStringStream(languages)
                        .map(code -> typeService.findItemByCode(code, LanguageType.class))
                        .forEach(target.getLanguages()::add),

                ROOMS, rooms -> getStringStream(rooms)
                        .map(code -> typeService.findItemByCode(code, RoomType.class))
                        .forEach(target.getRooms()::add),

                TAX_GROUPS, value -> getStringStream(value)
                        .map(code -> typeService.findItemByCode(code, TaxGroupType.class))
                        .forEach(target.getTaxGroups()::add),

                ADDRESS, addressCodes -> getStringStream(addressCodes)
                        .map(code -> typeService.findItemByCode(code, AddressType.class))
                        .forEach(target.getAddresses()::add)));
        return fieldsMapper;
    }
}
