package bi.manager.core.services;

import bi.manager.core.types.MBRentPropertyType;

import java.util.Collection;

public interface MBRentService extends MBTypeService<MBRentPropertyType>{
    /**
     * Gets all the rental properties that belong to the facility {@link String facilityCode} which are active
     *
     * @param facilityCode
     * @return collection of rental properties
     */
    Collection<MBRentPropertyType> getRentsByFacilityCode(String facilityCode);

    /**
     * Finds a rental property with {@link MBRentPropertyType#getCode()} if found the property is updated,
     * if the property is not found a new will be created and saved
     *
     * @param rent
     */
    void updateRent(MBRentPropertyType rent);
}
