package bi.uburaro.initialdata.mappers.impl.manager;

import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.MBInventoryType;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.initialdata.mappers.impl.AbstractTypeMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static bi.manager.core.types.MBCategoryType.*;

public class MBCategoryTypeMapper extends AbstractTypeMapper<MBCategoryType> {


    protected MBCategoryTypeMapper(TypeService typeService) {
        super(typeService);
    }

    @Override
    public Class<MBCategoryType> getTargetClass() {
        return MBCategoryType.class;
    }

    @Override
    public Map<String, Consumer<String>> createFieldsMapper(final MBCategoryType target) {
        final Map<String, Consumer<String>> fieldsMapper = new HashMap<>();

        fieldsMapper.putAll(Map.of(
                CODE, target::setCode,
                ACTIVE, value -> target.setActive(Boolean.valueOf(value)),
                VISIBLE, value -> target.setVisible(Boolean.valueOf(value)),
                NAME, target::setName,
                FACILITY, facility -> target.setFacility(typeService.findItemByCode(facility, MBFacilityType.class))
        ));

        fieldsMapper.putAll(Map.of(
                INVENTORIES, inventories -> getStringStream(inventories)
                        .map(code -> typeService.findItemByCode(code, MBInventoryType.class))
                        .forEach(target.getInventories()::add)

        ));
        return fieldsMapper;
    }
}
