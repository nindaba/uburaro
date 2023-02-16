package bi.uburaro.initialdata.mappers.impl.manager;

import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.core.types.client.MBRentOrderType;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.initialdata.mappers.impl.AbstractTypeMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static bi.manager.core.types.client.MBClientType.*;

public class MBClientTypeMapper extends AbstractTypeMapper<MBClientType> {


    protected MBClientTypeMapper(TypeService typeService) {
        super(typeService);
    }

    @Override
    public Class<MBClientType> getTargetClass() {
        return MBClientType.class;
    }

    @Override
    public Map<String, Consumer<String>> createFieldsMapper(final MBClientType target) {
        final Map<String, Consumer<String>> fieldsMapper = new HashMap<>();

        fieldsMapper.putAll(Map.of(
                CODE, target::setCode,
                ACTIVE, value -> target.setActive(Boolean.valueOf(value)),
                VISIBLE, value -> target.setVisible(Boolean.valueOf(value)),
                NAME, target::setName,
                ADDRESS, target::setAddress,
                TOTAL_DEBT, Long::parseLong
        ));

        fieldsMapper.putAll(Map.of(
                INVOICES, inventories -> getStringStream(inventories)
                        .map(code -> typeService.findItemByCode(code, MBInvoiceType.class))
                        .forEach(target.getInvoices()::add),
                RENT_ORDERS, rents -> getStringStream(rents)
                        .map(code -> typeService.findItemByCode(code, MBRentOrderType.class))
                        .forEach(target.getRentOrders()::add),
                INVENTORY_ORDERS, inventories -> getStringStream(inventories)
                        .map(code -> typeService.findItemByCode(code, MBInventoryOrderType.class))
                        .forEach(target.getInventoryOrders()::add),
                FACILITY, facility -> target.setFacility(typeService.findItemByCode(facility, MBFacilityType.class))

        ));
        return fieldsMapper;
    }
}
