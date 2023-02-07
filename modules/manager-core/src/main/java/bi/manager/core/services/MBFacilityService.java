package bi.manager.core.services;

import bi.manager.core.types.MBFacilityType;

import java.util.Collection;
import java.util.Set;

public interface MBFacilityService {

    /**
     * finds a facility by {@code code}
     *
     * @param code
     * @return facility, and if it is not found null will be returned
     */
    MBFacilityType getFacilityByCode(String code);
    /**
     * Finds all the facilities
     *
     * @return a collection of facilities
     */
    Collection<MBFacilityType> getAllFacilities();

    /**
     * Disables all the facilities related with the ids provided
     * @param facilityIds
     */
    void deleteFacilities(Set<String> facilityIds);

    /**
     * Checks if there is already a facility with the facility code same to the {@code facility}
     * and activates it, and finally saves it
     *
     * @param facility
     * @return facility
     */
    MBFacilityType createFacility(MBFacilityType facility);

    /**
     * updates the facility
     *
     * @param facility
     */
    void updateFacility(MBFacilityType facility);
}
