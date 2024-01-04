package bi.manager.core.services;

import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.enums.MBInventoryEntryEnum;
import bi.manager.core.utils.MBPage;
import bi.manager.core.utils.MBPageable;

import java.time.LocalDate;
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

    /**
     * Get paged inventory orders which belong to the facility, and placed in the range
     *
     * @param code
     * @param from
     * @param to
     * @param pageable
     * @return page of inventory orders
     */
    MBPage<MBInventoryOrderType> getOrderByFacilityCode(String code, MBInventoryEntryEnum orderType, LocalDate from, LocalDate to, MBPageable pageable);

    Collection<MBInventoryOrderType> getOrderByFacilityCode(String code, MBInventoryEntryEnum orderType, LocalDate from, LocalDate to);

    long getTotalAmount(String facility, LocalDate from, LocalDate to, MBInventoryEntryEnum entryType);
}
