package bi.manager.core.services;

import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBRentContractType;
import bi.manager.core.utils.MBPage;
import bi.manager.core.utils.MBPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collection;

public interface MBRentContractService extends MBTypeService<MBRentContractType> {
    /**
     * Get all the rental contracts by searching the active rent properties which belongs to the facility
     * and getting its active contracts
     *
     * @param code
     * @return collection of inventory contract
     */
    Collection<MBRentContractType> getContractsByFacilityCode(String code);

    /**
     * Get all the rent contracts by finding rent property which belongs to the code
     * {@link MBRentPropertyType#getContracts()} and filtering the active contracts
     *
     * @param code
     * @return collection of rent contract
     */
    Collection<MBRentContractType> getContractsByRentCode(String code);

    /**
     * Get all the rental contract by client and filtering the active contracts
     *
     * @param code
     * @return collection of rent contract
     */
    Collection<MBRentContractType> getContractsByClientCode(String code);

    /**
     * Adds a rental contract or updates it, it will also be added to the client
     * and changes the current client
     * <p>
     * finally saves the order
     *
     * @param contract
     */
    void updateContract(MBRentContractType contract);

    /**
     * changes the {@link MBRentContractType#setTo(LocalDate)} to endDate
     * and removes the currentContract from the rent property
     *
     * @param code
     * @param endDate
     */
    void endContract(String code,LocalDate endDate);

    /**
     * Get contracts which belongs to a {@code facility} within a given date range
     *
     * @param facility
     * @param from
     * @param to
     * @param pageable
     *
     * @return page of contracts
     */
    MBPage<MBRentContractType> getContracts(String facility, LocalDate from, LocalDate to, MBPageable pageable);

    Collection<MBRentContractType> getContracts(String facility, LocalDate from, LocalDate to);
}
