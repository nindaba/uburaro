package bi.manager.facade.facades;

import bi.manager.facade.data.MBFacilityData;

import java.util.Collection;
import java.util.Set;

public interface MBFacilityFacade {

    /**
     * Gets all active Facilities
     *
     * @param allFields determines if all the attributes will be included
     * @return Collection of facilities
     */
    Collection<MBFacilityData> getAllFacilities(boolean allFields);

    /**
     * Gets Facility based on code
     *
     * @param allFields determines if all the attributes will be included
     * @param code      of the facility
     * @return facility
     */
    MBFacilityData getFacilityByCode(String code, boolean allFields);

    /**
     * Deletes a facilities related to the codes provided
     * @param codes of the customer to be removed
     */
    void deleteFacilities(Set<String> codes);

    /**
     * Updates facility related to the code
     *
     * @param facility
     * @param code
     */
    void updateFacility(String code, MBFacilityData facility);

    /**
     * Creates a facility and saves it
     *
     * @param facilityData
     */
    void createFacility(MBFacilityData facilityData);
}
