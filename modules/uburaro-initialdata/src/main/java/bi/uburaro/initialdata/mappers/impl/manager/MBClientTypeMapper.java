package bi.uburaro.initialdata.mappers.impl.manager;

import bi.manager.core.repositories.MBOrderRepository;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBInvoiceType;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.initialdata.mappers.impl.AbstractTypeMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static bi.manager.core.types.client.MBClientType.*;

public class MBClientTypeMapper extends AbstractTypeMapper<MBClientType> {

    protected final MBOrderRepository orderRepository;

    protected MBClientTypeMapper(TypeService typeService, MBOrderRepository orderRepository) {
        super(typeService);
        this.orderRepository = orderRepository;
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
                TOTAL_DEBT, value -> setTotalDebt(target,value)
        ));

        fieldsMapper.putAll(Map.of(
                INVOICES, inventories -> getStringStream(inventories)
                        .map(code -> typeService.findItemByCode(code, MBInvoiceType.class))
                        .forEach(target.getInvoices()::add),
                ORDERS, orderNumbers -> getStringStream(orderNumbers)
                        .map(orderRepository::findByOrderNumber)
                        .forEach(target.getOrders()::add),
                FACILITY, facility -> target.setFacility(typeService.findItemByCode(facility, MBFacilityType.class))

        ));
        return fieldsMapper;
    }

    private void setTotalDebt(final MBClientType target, final String value) {
        final long totalDebt = -Long.parseLong(value);
        if(totalDebt < 0){
            target.setTotalDebt(totalDebt);
        }
    }
}
