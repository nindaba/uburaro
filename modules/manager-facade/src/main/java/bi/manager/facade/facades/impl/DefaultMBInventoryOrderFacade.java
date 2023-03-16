package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBInventoryOrderService;
import bi.manager.facade.converters.order.InventoryOrderMapper;
import bi.manager.facade.data.MBInventoryOrderData;
import bi.manager.facade.facades.MBInventoryOrderFacade;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service(value = "mBInventoryOrderFacade")
public class DefaultMBInventoryOrderFacade implements MBInventoryOrderFacade {
    protected final MBInventoryOrderService orderService;
    protected final InventoryOrderMapper mapper;

    public DefaultMBInventoryOrderFacade(MBInventoryOrderService orderService, InventoryOrderMapper inventoryOrderMapper) {
        this.orderService = orderService;
        this.mapper = inventoryOrderMapper;
    }

    @Override
    public Collection<MBInventoryOrderData> getOrderByFacilityCode(final String code) {
        return mapper.inventoriesToData(
                orderService.getOrderByFacilityCode(code)
        );
    }

    @Override
    public Collection<MBInventoryOrderData> getOrderByInventoryCode(final String code) {
        return mapper.inventoriesToData(
                orderService.getOrderByInventoryCode(code)
        );
    }

    @Override
    public Collection<MBInventoryOrderData> getOrderByClientCode(final String code) {
        return mapper.inventoriesToData(
                orderService.getOrderByClientCode(code)
        );
    }

    @Override
    public void placeOrder(final MBInventoryOrderData order) {
        orderService.placeOrder(mapper.inventoryOrderToType(order));
    }

    @Override
    public void deleteOrder(final Set<String> orderNumbers) {
        orderService.deleteOrder(orderNumbers);
    }
}
