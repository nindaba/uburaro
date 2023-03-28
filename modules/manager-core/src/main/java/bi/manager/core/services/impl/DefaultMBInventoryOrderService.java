package bi.manager.core.services.impl;

import bi.manager.core.repositories.MBInventoryOrderRepository;
import bi.manager.core.repositories.MBOrderRepository;
import bi.manager.core.services.*;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.MBInventoryType;
import bi.manager.core.types.client.MBOrderType;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.manager.core.types.enums.MBInventoryEntryEnum;
import bi.manager.core.utils.MBPage;
import bi.manager.core.utils.MBPageable;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.repositories.GeneratedKeyRepository;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.GeneratedKey;
import bi.uburaro.core.types.ItemType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static bi.manager.core.ManagerCoreConstants.INVENTORY_ORDER_PREFIX;

@Service(value = "mBInventoryOrderService")
public class DefaultMBInventoryOrderService extends AbstractOrderService implements MBInventoryOrderService {

    protected final MBInventoryService inventoryService;
    protected final MBCapitalService capitalService;
    protected final ItemRepository itemRepository;
    protected final MBInventoryOrderRepository inventoryOrderRepository;

    public DefaultMBInventoryOrderService(MBFacilityService facilityService, MBClientService clientService, MBInventoryService inventoryService, TypeService typeService, GeneratedKeyRepository generatedKeyRepository, Environment environment, MBCapitalService capitalService, MBOrderRepository orderRepository, ItemRepository itemRepository, MBInventoryOrderRepository inventoryOrderRepository) {
        super(facilityService, clientService, typeService, generatedKeyRepository, environment, orderRepository);
        this.inventoryService = inventoryService;
        this.capitalService = capitalService;
        this.itemRepository = itemRepository;
        this.inventoryOrderRepository = inventoryOrderRepository;
    }


    @Override
    public Collection<MBInventoryOrderType> getOrderByFacilityCode(final String code) {
        return facilityService.getFacilityByCode(code)
                .getCategories().stream()
                .filter(ItemType::isActive)
                .flatMap(category -> category.getInventories().stream())
                .filter(ItemType::isActive)
                .flatMap(inventory -> inventory.getInventoryOrders().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<MBInventoryOrderType> getOrderByInventoryCode(final String code) {
        return inventoryService.getInventoryByCode(code).getInventoryOrders();
    }

    @Override
    public Collection<MBInventoryOrderType> getOrderByClientCode(final String code) {
        return clientService.getClientByCode(code).getOrders().stream()
                .filter(order -> order instanceof MBInventoryOrderType)
                .map(order -> (MBInventoryOrderType) order)
                .collect(Collectors.toList());
    }

    @Override
    public void placeOrder(final MBInventoryOrderType order) {
        final MBInventoryOrderType orderType = typeService.create(MBInventoryOrderType.class);
        populateOrder(order, orderType);
        populateInventory(order, orderType);

        if (orderType.getOrderEntry() == MBInventoryEntryEnum.SOLD) {
            populateClient(order, orderType);
            chargeClient(orderType);
        }

        addCapitalEntry(orderType);
        changeStockLevels(orderType);
        typeService.save(orderType);
    }

    private void addCapitalEntry(MBInventoryOrderType order) {
        if (MBInventoryEntryEnum.REFILL == order.getOrderEntry()) {
            MBFacilityType facility = order.getInventory().getCategory().getFacility();
            capitalService.addCapital(order.getCost() * order.getQuantity(), MBEntryEnum.EXPENSE, facility);
        }

    }

    private void changeStockLevels(final MBInventoryOrderType orderType) {
        final MBInventoryType inventory = orderType.getInventory();
        Integer newQuantity = inventory.getQuantity();
        Integer orderQuantity = orderType.getQuantity();

        if (orderType.getOrderEntry() != MBInventoryEntryEnum.REFILL
                && inventory.getQuantity() < orderQuantity) {
            throw new NotFoundException("inventory.quantity.error");
        }

        newQuantity += orderType.getOrderEntry() == MBInventoryEntryEnum.REFILL ? orderQuantity : -orderQuantity;
        inventory.setQuantity(newQuantity);
    }


    private void populateInventory(final MBInventoryOrderType source, final MBInventoryOrderType target) {

        MBInventoryType sourceInventory = source.getInventory();
        if (sourceInventory == null || StringUtils.isEmpty(sourceInventory.getCode())) {
            throw new NotFoundException("No Inventory was found on the order");
        }

        final MBInventoryType inventory = inventoryService.getInventoryByCode(sourceInventory.getCode());

        if (source.getCost() > 0) {
            target.setCost(source.getCost());
        }
        if (source.getOrderEntry() == MBInventoryEntryEnum.SOLD && target.getCost() <= 0 && inventory.getCost() <= 0) {
            throw new NotFoundException("No cost was found on either the Inventory nor the Order");
        }
        if (target.getCost() <= 0) {
            target.setCost(inventory.getCost());
        }
        if (source.getOrderEntry() != MBInventoryEntryEnum.SOLD) {
            inventory.setCost(source.getCost());
        }
        target.setInventory(inventory);
    }

    private void populateOrder(final MBInventoryOrderType source, final MBInventoryOrderType target) {
        if (source.getQuantity() <= 0) {
            throw new NotFoundException("NO Quantity was found on the order");
        }
        target.setQuantity(source.getQuantity());

        target.setOrderDate(source.getOrderDate());
        if (source.getOrderDate() == null) {
            target.setOrderDate(LocalDate.now());
        }
        GeneratedKey generated = new GeneratedKey();
        generatedKeyRepository.save(generated);

        String prefix = environment.getProperty(INVENTORY_ORDER_PREFIX, String.class, "IN-");
        target.setOrderNumber(prefix + generated.getGeneratedValue());
        target.setOrderEntry(source.getOrderEntry());
    }

    @Override
    public void deleteOrder(Set<String> orderNumber) {
        orderNumber.stream()
                .map(orderRepository::findByOrderNumber)
                .filter(order -> !order.isPaid())
                .peek(order -> {
                    revertClient(order);
                    revertStockLevels(order);
                    revertCapital(order);
                })
                .forEach(orderRepository::delete);
    }

    @Override
    public MBPage<MBInventoryOrderType> getOrderByFacilityCode(final String code, final MBInventoryEntryEnum orderType, final LocalDate from, final LocalDate to, final MBPageable pageable) {
        final Page<MBInventoryOrderType> page = inventoryOrderRepository.findAllByFacilityAndDateRange(code, from, to, orderType, pageable);
        return new MBPage<>(page);
    }

    @Override
    public long getTotalAmount(final String facility, final LocalDate from, final LocalDate to, final MBInventoryEntryEnum entryType) {
        return inventoryOrderRepository.findAmountByFacilityAndDateRange(facility, from, to, entryType).stream()
                .map(order -> order.getCost() * order.getQuantity())
                .reduce(0l, Long::sum);
    }

    protected void revertStockLevels(final MBOrderType order) {
        if (order instanceof MBInventoryOrderType) {
            final MBInventoryOrderType inventoryOrder = (MBInventoryOrderType) order;
            final MBInventoryType inventory = inventoryOrder.getInventory();
            int quantity = inventory.getQuantity();

            if (inventoryOrder.getOrderEntry() == MBInventoryEntryEnum.REFILL) {
                quantity -= order.getQuantity();
            } else {
                quantity += order.getQuantity();
            }

            inventory.setQuantity(quantity);
            itemRepository.save(inventory);
        }

    }

    protected void revertCapital(MBOrderType order) {
        if (order instanceof MBInventoryOrderType && ((MBInventoryOrderType) order).getOrderEntry() == MBInventoryEntryEnum.REFILL) {
            MBFacilityType facility = ((MBInventoryOrderType) order).getInventory().getCategory().getFacility();
            MBCapitalType capital = facility.getCapital();

            long cost = order.getCost() * order.getQuantity();
            capital.getEntries().stream()
                    .filter(entry -> DateUtils.isSameDay(order.getDateModified(), entry.getDateModified()))
                    .filter(entry -> entry.getAmount() == cost)
                    .findFirst()
                    .stream()
                    .peek(entryType -> capital.getEntries().remove(entryType))
                    .forEach(itemRepository::delete);

            capital.setCurrentValue(capital.getCurrentValue() + cost);
            itemRepository.save(capital);
        }
    }

}
