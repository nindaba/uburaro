package bi.manager.core.services;

import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.client.MBOrderType;

import java.util.Collection;
import java.util.Set;

public interface MBInventoryOrderService {

    /**
     * Get all the inventory orders by {@code facilityCode}
     *
     * @param code
     * @return collection of inventory orders
     */
    Collection<MBInventoryOrderType> getOrderByFacilityCode(String code);

    /**
     * Get all the inventory orders by inventory
     *
     * @param code
     * @return collection of inventory orders
     */
    Collection<MBInventoryOrderType> getOrderByInventoryCode(String code);

    /**
     * Get all the inventory orders by client
     *
     * @param code
     * @return collection of inventory orders
     */
    Collection<MBInventoryOrderType> getOrderByClientCode(String code);

    /**
     * Add an inventory order, if the orderEntry is SOLD, then
     * it will add added to the client
     * and if it is REFILL, it will add expense to the facility
     * and if it is OUT, it will just reduce the quantity of the inventory
     *
     * finally saves the order
     *
     * @param order
     */
    void placeOrder(MBInventoryOrderType order);

    /**
     * Delete order, this will revert the quantity to the inventory and then deletes
     *
     * @param orderNumber
     */
    void deleteOrder(Set<String> orderNumber);

}
