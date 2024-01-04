package bi.uburaro.initialdata.mappers.impl.manager;

import bi.manager.core.repositories.MBOrderRepository;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBRentContractType;
import bi.manager.core.types.client.MBRentOrderType;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.initialdata.mappers.impl.AbstractTypeMapper;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static bi.manager.core.types.client.MBRentContractType.*;

public class MBRentContractTypeMapper extends AbstractTypeMapper<MBRentContractType> {

    protected final MBOrderRepository orderRepository;
    protected MBRentContractTypeMapper(TypeService typeService, MBOrderRepository orderRepository) {
        super(typeService);
        this.orderRepository = orderRepository;
    }

    @Override
    public Class<MBRentContractType> getTargetClass() {
        return MBRentContractType.class;
    }

    @Override
    public Map<String, Consumer<String>> createFieldsMapper(final MBRentContractType target) {
        final Map<String, Consumer<String>> fieldsMapper = new HashMap<>();

        fieldsMapper.putAll(Map.of(
                CODE, target::setCode,
                ACTIVE, value -> target.setActive(Boolean.valueOf(value)),
                VISIBLE, value -> target.setVisible(Boolean.valueOf(value)),
                FROM, value -> target.setFrom(LocalDate.parse(value)),
                TO, value -> target.setTo(LocalDate.parse(value)),
                UNIT, value -> target.setUnit(Integer.parseInt(value)),
                COST_PER_UNIT, value -> target.setCostPerUnit(Long.parseLong(value))
        ));

        fieldsMapper.putAll(Map.of(
                CLIENT, code -> target.setClient(typeService.findItemByCode(code, MBClientType.class)),
                ORDERS, orderNumbers -> getStringStream(orderNumbers)
                        .map(orderRepository::findByOrderNumber)
                        .map(order -> (MBRentOrderType) order)
                        .forEach(target.getOrders()::add),
                RENT_PROPERTY, facility -> target.setRentProperty(typeService.findItemByCode(facility, MBRentPropertyType.class))

        ));
        return fieldsMapper;
    }
}
