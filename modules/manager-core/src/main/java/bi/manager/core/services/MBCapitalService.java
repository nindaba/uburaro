package bi.manager.core.services;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.enums.MBEntryEnum;

import java.util.Collection;
import java.util.Date;

public interface MBCapitalService {

    /**
     * Finds capital related to the {@code facility}
     * Creates a Capital entry with the value and add it to the capital
     *
     * @param facilityCode
     * @param value
     * @param type
     */
    void addCapital(String facilityCode, long value, MBEntryEnum type);


    /**
     * Creates a Capital entry with the value and add it to the capital of the facility provided
     *
     * @param facility
     * @param value
     * @param type
     */
    void addCapital(long value, MBEntryEnum type, MBFacilityType facility);

    /**
     * Gets a Capital that is related to the facility
     *
     * @param facilityCode
     * @return capital
     */
    MBCapitalType getCapitalByFacility(String facilityCode);

    /**
     * Get Entries of capital which belongs to a {@code facilityCode} with date that is in the given range
     *
     * @param facilityCode
     * @param from         Date
     * @param to           Date
     * @return collection of entries
     */
    Collection<MBCapitalEntryType> getCapitalEntries(String facilityCode, Date from, Date to);
}
