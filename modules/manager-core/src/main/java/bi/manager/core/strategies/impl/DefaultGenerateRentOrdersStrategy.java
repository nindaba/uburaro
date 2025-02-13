package bi.manager.core.strategies.impl;

import bi.manager.core.repositories.MBRentContractRepository;
import bi.manager.core.services.MBRentOrderService;
import bi.manager.core.strategies.GenerateRentOrdersStrategy;
import bi.manager.core.types.client.MBRentContractType;
import bi.manager.core.types.client.MBRentOrderType;
import bi.uburaro.core.types.ItemType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import static bi.manager.core.ManagerCoreConstants.RENT_UNIT_SCALE;
import static java.time.temporal.ChronoUnit.DAYS;

@Service(value = "generateRentOrdersStrategy")
class DefaultGenerateRentOrdersStrategy implements GenerateRentOrdersStrategy {

    protected final MBRentContractRepository rentContractRepository;
    protected final MBRentOrderService rentOrderService;

    public DefaultGenerateRentOrdersStrategy(final MBRentContractRepository rentContractRepository, final MBRentOrderService rentOrderService) {
        this.rentContractRepository = rentContractRepository;
        this.rentOrderService = rentOrderService;
    }

    @Override
    public Collection<MBRentContractType> generateOrders() {
        return this.rentContractRepository.findMBRentContractTypesByNextOrderDateBefore(LocalDate.now()).stream()
                .filter(ItemType::isActive)
                .filter(contract -> contract.getRentProperty().getCurrentContract() == contract)
                .peek(this::createOrdersAndSchedule)
                .map(rentContractRepository::save)
                .collect(Collectors.toList());
    }

    protected void createOrdersAndSchedule(final MBRentContractType contract) {
        while (this.canScheduleNextOrder(contract)) {
            rentOrderService.placeOrder(createOrder(contract));
            contract.setNextOrderDate(getNextOrderDate(contract));
        }

        if (contract.getNextOrderDate().isEqual(contract.getTo())) {
            contract.getRentProperty().setCurrentContract(null);
        }
    }

    protected boolean canScheduleNextOrder(final MBRentContractType contract) {
        return contract.getRentProperty().getCurrentContract() != null
               && contract.getNextOrderDate().isBefore(contract.getTo())
               && contract.getNextOrderDate().isBefore(LocalDate.now());
    }

    protected MBRentOrderType createOrder(final MBRentContractType contract) {
        final LocalDate orderDate = contract.getNextOrderDate();
        final MBRentOrderType order = new MBRentOrderType();

        order.setContract(contract);
        order.setRentProperty(contract.getRentProperty());
        order.setFrom(orderDate);
        order.setTo(getNextOrderDate(contract));
        order.setOrderDate(orderDate);
        order.setClient(contract.getClient());

        return order;
    }



    private LocalDate getNextOrderDate(final MBRentContractType contract) {
        LocalDate nextOrderDate = contract.getNextOrderDate()
                .plus(1, RENT_UNIT_SCALE.getOrDefault(contract.getUnit(), DAYS));

        while (nextOrderDate.isAfter(contract.getTo())) {
            nextOrderDate = nextOrderDate.minusDays(1);
        }

        return nextOrderDate;
    }
}
