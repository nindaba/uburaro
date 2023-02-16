package bi.uburaro.initialdata.mappers.impl.manager;

import bi.manager.core.services.MBCapitalService;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.initialdata.mappers.impl.AbstractTypeMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static bi.manager.core.types.MBFacilityType.*;

public class MBFacilityTypeMapper extends AbstractTypeMapper<MBFacilityType> {

    protected final MBCapitalService mbCapitalService;
    public MBFacilityTypeMapper(TypeService typeService, MBCapitalService mbCapitalService) {
        super(typeService);
        this.mbCapitalService = mbCapitalService;
    }

    @Override
    public Class<MBFacilityType> getTargetClass() {
        return MBFacilityType.class;
    }

    @Override
    public Map<String, Consumer<String>> createFieldsMapper(final MBFacilityType target) {
        final Map<String, Consumer<String>> fieldsMapper = new HashMap<>();

        fieldsMapper.putAll(Map.of(
                CODE, target::setCode,
                ACTIVE, value -> target.setActive(Boolean.valueOf(value)),
                VISIBLE, value -> target.setVisible(Boolean.valueOf(value)),
                NAME, target::setName,
                ALIAS, target::setAlias,
                ADDRESS, target::setAddress,
                CAPITAL, value -> addCapital(target, value)
        ));

        fieldsMapper.putAll(Map.of(
                CLIENTS, clients -> getStringStream(clients)
                        .map(code -> typeService.findItemByCode(code, MBClientType.class))
                        .forEach(target.getClients()::add),

                CATEGORIES, categories -> getStringStream(categories)
                        .map(code -> typeService.findItemByCode(code, MBCategoryType.class))
                        .forEach(target.getCategories()::add)

        ));
        return fieldsMapper;
    }

    private void addCapital(MBFacilityType target, String value) {
        if (target.getCapital() == null) {
            mbCapitalService.addCapital(Long.parseLong(value), MBEntryEnum.EXTERNAL,target);
        }
    }
}
