package bi.manager.facade.facades;

import bi.manager.facade.data.MBInventoryData;

import java.util.Collection;
import java.util.Set;

public interface MBInventoryFacade {
    /**
     * Gets all the active inventories for the category and populates data objects
     *
     * @param categoryCode
     * @param allFields
     * @return collection of Inventories
     */
    Collection<MBInventoryData> getInventoriesByCategory(String categoryCode,boolean allFields);


    /**
     * Gets an inventory by its code
     *
     * @param code
     * @param allFields
     * @return inventory
     */
    MBInventoryData getInventoryByCode(String code,boolean allFields);

    /**
     * Finds an Inventory by {@code inventory.code} and update
     *
     * @param inventory
     */
    void updateInventory(MBInventoryData inventory);

    /**
     * Creates an inventory and saves it
     *
     * @param inventory
     */
    void createInventory(MBInventoryData inventory);

    /**
     * Disables all the inventories related to the {@code codes}
     *
     * @param codes
     */
    void deleteInventory(Set<String> codes);

    /**
     * Finds all the related categories and retrieve there inventories
     *
     * @param facilityCode
     * @return collection of inventories
     */
    Collection<MBInventoryData> getInventoryByFacilityCode(String facilityCode);
}
