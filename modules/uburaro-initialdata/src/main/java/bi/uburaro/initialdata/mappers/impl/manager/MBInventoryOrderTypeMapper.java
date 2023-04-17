package bi.uburaro.initialdata.mappers.impl.manager;

import bi.manager.core.services.MBInventoryOrderService;
import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.MBInventoryType;
import bi.manager.core.types.enums.MBInventoryEntryEnum;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.importer.BatchLineType;
import bi.uburaro.initialdata.mappers.impl.AbstractTypeMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static bi.manager.core.types.MBInventoryOrderType.*;

public class MBInventoryOrderTypeMapper extends AbstractTypeMapper<MBInventoryOrderType> {


    protected MBInventoryOrderTypeMapper(TypeService typeService) {
        super(typeService);
    }

    @Override
    public Class<MBInventoryOrderType> getTargetClass() {
        return MBInventoryOrderType.class;
    }

    @Override
    public Map<String, Consumer<String>> createFieldsMapper(final MBInventoryOrderType target) {
        final Map<String, Consumer<String>> fieldsMapper = new HashMap<>();

        fieldsMapper.putAll(Map.of(
                COST, value -> setCostToOrder(target, value),
                QUANTITY, value -> setQuantity(target, value),
                INVENTORY, value -> setInventory(target,value)
        ));
        return fieldsMapper;
    }

    private void setInventory(MBInventoryOrderType target, String value) {
        target.setInventory(typeService.findItemByCode(value, MBInventoryType.class));
    }


    private void setQuantity(MBInventoryOrderType order,String value) {
        order.setQuantity(Integer.parseInt(value));
        order.setOrderEntry(MBInventoryEntryEnum.REFILL);
    }

    private void setCostToOrder(MBInventoryOrderType target, String value) {
        target.setCost(Long.parseLong(value));
    }
}
