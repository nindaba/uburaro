package bi.manager.facade.facades;

import bi.manager.core.services.MBRentService;
import bi.manager.core.services.MBTypeService;
import bi.manager.core.types.MBRentPropertyType;
import bi.manager.facade.data.MBRentPropertyData;

import java.util.Collection;
import java.util.Set;

public interface MBRentPropertyFacade {
    /**
     * Get all the rentals using {@link MBRentService#getRentsByFacilityCode(String)}
     * than populate the data object depending on {@link Boolean allFields}
     *
     * @param allFields
     * @param facilityCode
     * @return collection of rental properties
     */
    Collection<MBRentPropertyData> getRentsByFacilityCode(String facilityCode, boolean allFields);

    /**
     * Gets a client by {@link  MBRentPropertyData#getCode()}
     * than populate the data object depending on {@link Boolean allFields}
     *
     * @param allFields
     * @param code
     * @return rental property
     */
    MBRentPropertyData getRentalPropertyByCode(String code, boolean allFields);

    /**
     * Deletes all the rental properties using {@link MBTypeService#deleteMBItem(Set)}
     *
     * @param codes
     */
    void deleteRentals(Set<String> codes);

    /**
     * converts the client data into type
     * then uses {@link MBRentService#updateRent(MBRentPropertyType)} to create or update a rental property
     *
     * @param rental
     */
    void updateRental(MBRentPropertyData rental);
}
