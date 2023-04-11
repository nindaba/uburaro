package bi.manager.facade.facades;

import bi.manager.core.services.MBRentOrderService;
import bi.manager.core.types.client.MBRentOrderType;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.MBPageData;
import bi.manager.facade.data.MBPageableData;
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

    /**
     * Gets all the orders that belong to the contract
     *
     * @param code
     * @return a collection of orders
     */
    Collection<MBRentOrderData> getOrdersByContract(String code);

    /**
     * Gets all the rent orders which belong to the {@code  facility} and in {@code range}
     *
     * @param facility
     * @param page
     * @return page of rent orders
     */
    MBPageData<MBRentOrderData> getOrderByFacilityCode(String facility, MBDateRangeData range, MBPageableData page);

    Collection<MBRentOrderData> getOrderByFacilityCode(String code, MBDateRangeData range);
}
