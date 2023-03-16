package bi.manager.core.services.impl;

import bi.manager.core.repositories.MBOrderRepository;
import bi.manager.core.services.MBClientService;
import bi.manager.core.services.MBFacilityService;
import bi.manager.core.services.MBRentOrderService;
import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBRentContractType;
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

import static bi.manager.core.ManagerCoreConstants.RENT_ORDER_PREFIX;
import static bi.manager.core.ManagerCoreConstants.RENT_UNIT_SCALE;

@Service(value = "mBRentOrderService")
public class DefaultMBRentOrderService extends AbstractOrderService implements MBRentOrderService {



    protected DefaultMBRentOrderService(MBFacilityService facilityService,
                                        MBClientService clientService,
                                        TypeService typeService,
                                        GeneratedKeyRepository generatedKeyRepository,
                                        Environment environment,
                                        MBOrderRepository orderRepository) {
        super(facilityService, clientService, typeService, generatedKeyRepository, environment, orderRepository);
    }

    @Override
    public Collection<MBRentOrderType> getOrderByFacilityCode(final String code) {
        return facilityService.getFacilityByCode(code).getRents().stream()
                .filter(ItemType::isActive)
                .flatMap(rent -> rent.getContracts().stream())
                .filter(ItemType::isActive)
                .flatMap(contract -> contract.getOrders().stream())
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<MBRentOrderType> getOrderByRentCode(final String code) {
        return typeService.findItemByCode(code, MBRentPropertyType.class).getContracts().stream()
                .filter(ItemType::isActive)
                .flatMap(contract -> contract.getOrders().stream())
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<MBRentOrderType> getOrderByClientCode(final String code) {
        return clientService.getClientByCode(code).getOrders().stream()
                .filter(orderType -> orderType instanceof MBRentOrderType)
                .map(orderType -> (MBRentOrderType) orderType)
                .collect(Collectors.toSet());
    }
    @Override
    public Collection<MBRentOrderType> getOrdersByContract(String code) {
        return typeService.findItemByCode(code, MBRentContractType.class).getOrders();
    }
    @Override
    public void placeOrder(final MBRentOrderType order) {
        final MBRentOrderType rentOrder = typeService.create(MBRentOrderType.class);
        final GeneratedKey generatedKey = generatedKeyRepository.save(new GeneratedKey());
        final String prefix = environment.getProperty(RENT_ORDER_PREFIX, String.class, "RO-");
        rentOrder.setOrderNumber(prefix + generatedKey.getGeneratedValue());

        populateOrder(order, rentOrder);
        populateContract(order, rentOrder);
        populateQuantity(rentOrder);
        populateClient(order, rentOrder);
        chargeClient(rentOrder);
        addIncome(rentOrder);
        typeService.save(rentOrder);
    }

    private void populateQuantity(final MBRentOrderType target) {
        final long quantity = RENT_UNIT_SCALE.getOrDefault(target.getUnit(), ChronoUnit.MONTHS).between(target.getFrom(), target.getTo());
        target.setQuantity((int) quantity);
    }

    private void populateContract(final MBRentOrderType source, final MBRentOrderType target) {
        final MBRentContractType sourceContract = source.getContract();
        if(sourceContract == null || StringUtils.isEmpty(sourceContract.getCode())){
            throw new NotFoundException("No Contract was fount on the order");
        }
        MBRentContractType contract = typeService.findItemByCode(sourceContract.getCode(), MBRentContractType.class);

        target.setContract(contract);
        target.setRentProperty(contract.getRentProperty());

        if (source.getUnit() == 0) {
            target.setUnit(contract.getUnit());
        }
        else{
            target.setUnit(source.getUnit());
        }

        if (source.getCost() == 0) {
            target.setCost(contract.getCostPerUnit());
        } else {
            target.setCost(source.getCost());
        }
    }

    private void addIncome(final MBRentOrderType rentOrder) {
        final MBRentPropertyType rentProperty = rentOrder.getRentProperty();
        final long income = rentProperty.getTotalIncome() + rentOrder.getQuantity() * rentOrder.getCost();
        rentProperty.setTotalIncome(income);
    }

    private void populateOrder(final MBRentOrderType source, final MBRentOrderType target) {
        target.setFrom(source.getFrom());
        target.setTo(source.getTo());
        target.setOrderDate(source.getFrom());
    }
    @Override
    public void deleteOrder(final Set<String> orderNumber) {
        orderNumber.stream()
                .map(orderRepository::findByOrderNumber)
                .filter(Objects::nonNull)
                .map(order -> (MBRentOrderType) order)
                .peek(this::revertClient)
                .peek(this::revertRentProperty)
                .forEach(typeService::delete);
    }

    private void revertRentProperty(final MBRentOrderType order) {
        final MBRentPropertyType rentProperty = order.getRentProperty();
        final long income = rentProperty.getTotalIncome() - order.getQuantity() * order.getCost();
        rentProperty.setTotalIncome(income);
        typeService.save(rentProperty);
    }

    public void revertClient(final MBRentOrderType order) {
        final MBClientType client = order.getClient();
        final long debt = client.getTotalDebt() + order.getQuantity() * order.getCost();
        client.setTotalDebt(debt);
        typeService.save(client);
    }
}
