package bi.manager.facade.facades;


import bi.manager.facade.data.MBCapitalData;
import bi.manager.facade.data.MBCapitalEntryData;

import java.util.Collection;
import java.util.Date;

public interface MBCapitalFacade {

    /**
     * Creates a Capital entry with the value and the capital type
     *
     * @param facilityCode
     * @param value
     * @param type capitalType
     */
    void addCapital(String facilityCode,long value, String type);

    /**
     * Gets a Capital that is related to the facility
     *
     * @param facilityCode
     * @return capital
     */
    MBCapitalData getCapitalByFacility(String facilityCode);

    /**
     * Get Entries of capital which belongs to a {@code facilityCode} with date that is in the given range
     *
     * @param facilityCode
     * @param from Date
     * @param to Date
     * @return collection of entries
     */
    Collection<MBCapitalEntryData> getCapitalEntries(String facilityCode, Date from, Date to);
}
