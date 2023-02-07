package bi.uburaro.initialdata.mappers.impl.manager;

import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBClientType;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.initialdata.mappers.impl.AbstractTypeMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static bi.manager.core.types.MBFacilityType.*;

public class MBFacilityTypeMapper extends AbstractTypeMapper<MBFacilityType> {

    public MBFacilityTypeMapper(TypeService typeService) {
        super(typeService);
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
                CAPITAL, value -> target.setCapital(typeService.findItemByCode(value, MBCapitalType.class))
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
}
