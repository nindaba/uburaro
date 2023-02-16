package bi.uburaro.initialdata.mappers.impl.manager;

import bi.manager.core.types.*;
import bi.manager.core.types.MBInventoryType;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.initialdata.mappers.impl.AbstractTypeMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static bi.manager.core.types.MBInventoryType.*;

public class MBInventoryTypeMapper extends AbstractTypeMapper<MBInventoryType> {


    protected MBInventoryTypeMapper(TypeService typeService) {
        super(typeService);
    }

    @Override
    public Class<MBInventoryType> getTargetClass() {
        return MBInventoryType.class;
    }

    @Override
    public Map<String, Consumer<String>> createFieldsMapper(final MBInventoryType target) {
        final Map<String, Consumer<String>> fieldsMapper = new HashMap<>();

        fieldsMapper.putAll(Map.of(
                CODE, target::setCode,
                ACTIVE, value -> target.setActive(Boolean.valueOf(value)),
                VISIBLE, value -> target.setVisible(Boolean.valueOf(value)),
                NAME, target::setName,
                COST, value -> target.setCost(Long.parseLong(value)),
                QUANTITY, value -> target.setQuantity(Integer.parseInt(value)),
                CATEGORY, category -> target.setCategory(typeService.findItemByCode(category, MBCategoryType.class))
        ));

        fieldsMapper.putAll(Map.of(
                INVENTORY_ORDERS, orders -> getStringStream(orders)
                        .map(code -> typeService.findItemByCode(code, MBInventoryOrderType.class))
                        .forEach(target.getInventoryOrders()::add)

        ));
        return fieldsMapper;
    }
}
