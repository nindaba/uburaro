package bi.manager.core.services;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.manager.core.utils.MBPage;
import bi.manager.core.utils.MBPageable;

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
     * Creates a Capital entry with the value,description and add it to the capital of the facility provided
     *
     * @param entry
     * @param facility
     */
    void addCapital(MBCapitalEntryType entry, String facility);

    /**
     * Adds a capital entry if th invoice din't have a capital entry,
     * but if it has a capital entry it will just update it
     *
     * @param invoice
     */
    void addCapital(MBInvoiceType invoice);

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

    /**
     * Get Entries of capital which belongs to a facilityCode with date that is in the given range
     *
     * @param facilityCode
     * @param from
     * @param to
     * @return page of capital Entries
     */
    MBPage<MBCapitalEntryType> getCapitalEntries(String facilityCode, Date from, Date to, MBPageable pageable);

    long getTotalAmount(String facilityCode, Date from, Date to, MBEntryEnum entryType);
}
