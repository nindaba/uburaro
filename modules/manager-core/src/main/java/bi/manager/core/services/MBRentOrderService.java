package bi.manager.core.services;

import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBRentOrderType;

import java.util.Collection;
import java.util.Set;

public interface MBRentOrderService {

    /**
     * Get all the rental orders by searching the rent properties which belongs to the facility
     *
     * @param code
     * @return collection of inventory orders
     */
    Collection<MBRentOrderType> getOrderByFacilityCode(String code);

    /**
     * Get all the rent orders by finding rent property which belongs to the code
     * {@link MBRentPropertyType#getRentOrders()}
     *
     * @param code
     * @return collection of rent orders
     */
    Collection<MBRentOrderType> getOrderByRentCode(String code);

    /**
     * Get all the rental orders by client
     *
     * @param code
     * @return collection of rent orders
     */
    Collection<MBRentOrderType> getOrderByClientCode(String code);

    /**
     * Add a rental order, it will also be added to the client
     * and changes the current cline
     * <p>
     * finally saves the order
     *
     * @param order
     */
    void placeOrder(MBRentOrderType order);

    /**
     * Delete order, and this will remove the order from the client and also reverts his debts
     *
     * @param orderNumber
     */
    void deleteOrder(Set<String> orderNumber);

}