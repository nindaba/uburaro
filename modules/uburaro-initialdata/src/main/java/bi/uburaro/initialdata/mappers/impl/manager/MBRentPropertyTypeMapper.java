package bi.uburaro.initialdata.mappers.impl.manager;

import bi.manager.core.repositories.MBOrderRepository;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBRentOrderType;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.initialdata.mappers.impl.AbstractTypeMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static bi.manager.core.types.MBRentPropertyType.*;

public class MBRentPropertyTypeMapper extends AbstractTypeMapper<MBRentPropertyType> {

    protected final MBOrderRepository orderRepository;

    protected MBRentPropertyTypeMapper(TypeService typeService, MBOrderRepository orderRepository) {
        super(typeService);
        this.orderRepository = orderRepository;
    }

    @Override
    public Class<MBRentPropertyType> getTargetClass() {
        return MBRentPropertyType.class;
    }

    @Override
    public Map<String, Consumer<String>> createFieldsMapper(final MBRentPropertyType target) {
        final Map<String, Consumer<String>> fieldsMapper = new HashMap<>();

        fieldsMapper.putAll(Map.of(
                CODE, target::setCode,
                ACTIVE, value -> target.setActive(Boolean.valueOf(value)),
                VISIBLE, value -> target.setVisible(Boolean.valueOf(value)),
                NAME, target::setName,
                ADDRESS, target::setAddress,
                UNIT, value -> target.setCost(Integer.parseInt(value)),
                COST, value -> target.setCost(Long.parseLong(value))
        ));

        fieldsMapper.putAll(Map.of(
                CURRENT_CLIENT, clientCode -> target.setCurrentClient(typeService.findItemByCode(clientCode, MBClientType.class)),
                RENT_ORDERS, orderNumbers -> getStringStream(orderNumbers)
                        .map(orderRepository::findByOrderNumber)
                        .map(order -> (MBRentOrderType) order)
                        .forEach(target.getRentOrders()::add),
                FACILITY, facility -> target.setFacility(typeService.findItemByCode(facility, MBFacilityType.class))

        ));
        return fieldsMapper;
    }
}