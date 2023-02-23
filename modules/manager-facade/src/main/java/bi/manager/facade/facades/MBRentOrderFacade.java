package bi.manager.facade.facades;

import bi.manager.core.services.MBRentOrderService;
import bi.manager.core.types.client.MBRentOrderType;
import bi.manager.facade.data.MBRentOrderData;

import java.util.Collection;
import java.util.Set;

public interface MBRentOrderFacade {

    /**
     * Get all the rent orders by {@code facilityCode}
     *
     * @param code
     * @return collection of rent orders
     */
    Collection<MBRentOrderData> getOrderByFacilityCode(String code);

    /**
     * Get all the rent orders by rent
     *
     * @param code
     * @return collection of rent orders
     */
    Collection<MBRentOrderData> getOrderByRentCode(String code);

    /**
     * Get all the rent orders by client
     *
     * @param code
     * @return collection of rent orders
     */
    Collection<MBRentOrderData> getOrderByClientCode(String code);

    /**
     * It will convert the order to {@link MBRentOrderType} and then
     * uses {@link MBRentOrderService#placeOrder(MBRentOrderType)}
     * to put the order in the system
     *
     * @param order
     */
    void placeOrder(MBRentOrderData order);

    /**
     * Delete order, using {@link MBRentOrderService#deleteOrder(Set<String>)}
     *
     * @param orderNumbers
     */
    void deleteOrder(Set<String> orderNumbers);
}
