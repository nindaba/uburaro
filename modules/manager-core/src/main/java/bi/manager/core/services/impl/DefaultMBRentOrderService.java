package bi.manager.core.services.impl;

import bi.manager.core.repositories.MBOrderRepository;
import bi.manager.core.services.MBClientService;
import bi.manager.core.services.MBFacilityService;
import bi.manager.core.services.MBRentOrderService;
import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBRentOrderType;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.repositories.GeneratedKeyRepository;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.GeneratedKey;
import bi.uburaro.core.types.ItemType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "mBRentOrderService")
public class DefaultMBRentOrderService extends AbstractOrderService implements MBRentOrderService {


    public static final String RENT_ORDER_PREFIX = "rent.order.prefix";

    protected DefaultMBRentOrderService(MBFacilityService facilityService,
                                        MBClientService clientService,
                                        TypeService typeService,
                                        GeneratedKeyRepository generatedKeyRepository,
                                        Environment environment,
                                        MBOrderRepository orderRepository) {
        super(facilityService, clientService, typeService, generatedKeyRepository, environment, orderRepository);
    }

    @Override
    public Collection<MBRentOrderType> getOrderByFacilityCode(String code) {
        return facilityService.getFacilityByCode(code).getRents().stream()
                .filter(ItemType::isActive)
                .flatMap(rent -> rent.getRentOrders().stream())
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<MBRentOrderType> getOrderByRentCode(String code) {
        return typeService.findItemByCode(code, MBRentPropertyType.class).getRentOrders();
    }

    @Override
    public Collection<MBRentOrderType> getOrderByClientCode(String code) {
        return clientService.getClientByCode(code).getOrders().stream()
                .filter(orderType -> orderType instanceof MBRentOrderType)
                .map(orderType -> (MBRentOrderType) orderType)
                .collect(Collectors.toSet());
    }

    @Override
    public void placeOrder(MBRentOrderType order) {
        MBRentOrderType rentOrder = typeService.create(MBRentOrderType.class);
        GeneratedKey generatedKey = generatedKeyRepository.save(new GeneratedKey());
        String prefix = environment.getProperty(RENT_ORDER_PREFIX, String.class, "RO-");
        rentOrder.setOrderNumber(prefix + generatedKey.getGeneratedValue());

        populateRent(order, rentOrder);
        populateOrder(order, rentOrder);
        populateClient(order, rentOrder);
        chargeClient(rentOrder);
        addIncome(rentOrder);
    }

    private void addIncome(MBRentOrderType rentOrder) {
        MBRentPropertyType rentProperty = rentOrder.getRentProperty();
        long income = rentProperty.getTotalIncome() + rentOrder.getUnit() * rentOrder.getCost();
        rentProperty.setTotalIncome(income);
    }

    protected void chargeClient(final MBRentOrderType orderType) {
        MBClientType client = orderType.getClient();
        long debt = client.getTotalDebt() - orderType.getUnitCharged() * orderType.getCost();
        orderType.setTotalUnitCharged(orderType.getTotalUnitCharged() + orderType.getUnitCharged());
        client.setTotalDebt(debt);
    }

    private void populateOrder(MBRentOrderType source, MBRentOrderType target) {
        int unit = target.getRentProperty().getUnit();

        if(source.getUnit()  == 0){
            target.setUnit(unit);
        }
        else {
            target.setUnit(source.getUnit());
        }
        target.setUnitCharged(source.getUnitCharged());
        target.setQuantity((int) (ChronoUnit.DAYS.between(target.getFrom(), target.getTo()) / unit));
        target.setFrom(source.getFrom());
        target.setTo(source.getTo());
        target.setOrderDate(source.getFrom());
    }

    private void populateRent(MBRentOrderType source, MBRentOrderType target) {
        MBRentPropertyType rentProperty = source.getRentProperty();
        if (rentProperty != null && StringUtils.isNotEmpty(rentProperty.getCode())) {
            MBRentPropertyType rent = typeService.findItemByCode(rentProperty.getCode(), MBRentPropertyType.class);
            rent.getRentOrders().add(target);
        } else {
            throw new NotFoundException("No property code found on the rent property");
        }
    }

    @Override
    public void deleteOrder(Set<String> orderNumber) {
        orderNumber.stream()
                .map(orderRepository::findByOrderNumber)
                .filter(Objects::nonNull)
                .map(order -> (MBRentOrderType) order)
                .peek(this::revertClient)
                .peek(this::revertRentProperty)
                .forEach(typeService::delete);
    }

    private void revertRentProperty(MBRentOrderType order) {
        MBRentPropertyType rentProperty = order.getRentProperty();
        long income = rentProperty.getTotalIncome() - order.getTotalUnitCharged() * order.getCost();
        rentProperty.setTotalIncome(income);
        rentProperty.setCurrentClient(null);
        typeService.save(rentProperty);
    }

    public void revertClient(MBRentOrderType order) {
        MBClientType client = order.getClient();
        long debt = client.getTotalDebt() + order.getTotalUnitCharged() * order.getCost();
        client.setTotalDebt(debt);
        typeService.save(client);
    }
}
