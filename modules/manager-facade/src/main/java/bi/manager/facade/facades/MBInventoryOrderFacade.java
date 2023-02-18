package bi.manager.facade.facades;

import bi.manager.core.services.MBInventoryOrderService;
import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.facade.data.MBInventoryOrderData;

import java.util.Collection;
import java.util.Set;

public interface MBInventoryOrderFacade {

    /**
     * Get all the inventory orders by {@code facilityCode}
     *
     * @param code
     * @return collection of inventory orders
     */
    Collection<MBInventoryOrderData> getOrderByFacilityCode(String code);

    /**
     * Get all the inventory orders by inventory
     *
     * @param code
     * @return collection of inventory orders
     */
    Collection<MBInventoryOrderData> getOrderByInventoryCode(String code);

    /**
     * Get all the inventory orders by client
     *
     * @param code
     * @return collection of inventory orders
     */
    Collection<MBInventoryOrderData> getOrderByClientCode(String code);

    /**
     * It will convert the order to {@link MBInventoryOrderType} and then
     * uses {@link MBInventoryOrderService#placeOrder(MBInventoryOrderType)}
     * to put the order in the system
     *
     * @param order
     */
    void placeOrder(MBInventoryOrderData order);

    /**
     * Delete order, using {@link MBInventoryOrderService#deleteOrder(Set<String>)}
     *
     * @param orderNumber
     */
    void deleteOrder(Set<String> orderNumbers);
}
