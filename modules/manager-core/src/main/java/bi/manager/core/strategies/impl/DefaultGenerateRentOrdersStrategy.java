package bi.manager.core.strategies.impl;

import bi.manager.core.repositories.MBRentContractRepository;
import bi.manager.core.services.MBRentOrderService;
import bi.manager.core.strategies.GenerateRentOrdersStrategy;
import bi.manager.core.types.client.MBRentContractType;
import bi.manager.core.types.client.MBRentOrderType;
import bi.uburaro.core.types.ItemType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.stream.Collectors;

import static bi.manager.core.ManagerCoreConstants.RENT_UNIT_SCALE;
import static java.time.temporal.ChronoUnit.DAYS;

@Service(value = "generateRentOrdersStrategy")
public class DefaultGenerateRentOrdersStrategy implements GenerateRentOrdersStrategy {

    protected final MBRentContractRepository rentContractRepository;
    protected final MBRentOrderService rentOrderService;



    public DefaultGenerateRentOrdersStrategy(MBRentContractRepository rentContractRepository, MBRentOrderService rentOrderService) {
        this.rentContractRepository = rentContractRepository;
        this.rentOrderService = rentOrderService;
    }

    @Override
    public Collection<MBRentContractType> generateOrders() {
        return this.rentContractRepository.findMBRentContractTypesByNextOrderDateAfter(LocalDate.now()).stream()
                .filter(ItemType::isActive)
                .filter(contract -> contract.getRentProperty().getCurrentContract() == contract)
                .filter(contract -> contract.getNextOrderDate().isBefore(LocalDate.now()))
                .peek(this::createOrder)
                .peek(this::scheduleNextOrderDate)
                .map(rentContractRepository::save)
                .collect(Collectors.toList());
    }

    protected void createOrder(final MBRentContractType contract) {
        final LocalDate orderDate = contract.getNextOrderDate();
        final ChronoUnit unit = RENT_UNIT_SCALE.getOrDefault(contract.getUnit(), DAYS);
        final MBRentOrderType order = new MBRentOrderType();
        order.setContract(contract);
        order.setRentProperty(contract.getRentProperty());
        order.setFrom(orderDate.minus(1, unit));
        order.setTo(orderDate);
        order.setOrderDate(LocalDate.now());
        order.setClient(contract.getClient());
        rentOrderService.placeOrder(order);
    }

    protected void scheduleNextOrderDate(final MBRentContractType contract) {
        if (contract.getNextOrderDate().isBefore(contract.getTo())) {
            setNextOrderDate(contract);
        } else {
            contract.getRentProperty().setCurrentContract(null);
        }
    }

    private void setNextOrderDate(MBRentContractType contract) {
        LocalDate nextOrderDate = contract.getNextOrderDate()
                .plus(1, RENT_UNIT_SCALE.getOrDefault(contract.getUnit(), DAYS));

        while (nextOrderDate.isAfter(contract.getTo())) {
            nextOrderDate = nextOrderDate.minusDays(1);
        }
        contract.setNextOrderDate(nextOrderDate);
    }
}
