package bi.manager.core.services;

import bi.manager.core.types.MBInventoryType;

import java.util.Collection;
import java.util.Set;

public interface MBInventoryService {
    /**
     * Gets all the Inventories related to the category {@code categoryCode} and
     * filters out the ones which are not active
     *
     * @param categoryCode
     * @return collection of Inventories
     */
    Collection<MBInventoryType> getInventoriesByCategory(String categoryCode);


    /**
     * Gets an inventory by its code
     *
     * @param code
     * @return inventory
     */
    MBInventoryType getInventoryByCode(String code);

    /**
     * Finds an Inventory by {@code inventory.code} and update
     *
     * @param inventory
     */
    void updateInventory(MBInventoryType inventory);

    /**
     * Finds all the inventories related with the {@code codes} and sets active to false
     * @param codes
     */
    void deleteInventory(Set<String> codes);

}
