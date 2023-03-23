package bi.manager.facade.facades;


import bi.manager.core.types.client.MBRentContractType;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.MBPageData;
import bi.manager.facade.data.MBPageableData;
import bi.manager.facade.data.MBRentContractData;
import java.util.Collection;
import java.util.Set;

public interface MBRentContractFacade {

    /**
     * Get all the rental contracts which belong to the facility
     *
     * @param code
     * @return collection of inventory contract
     */
    Collection<MBRentContractData> getContractsByFacilityCode(String code);

    /**
     * Get all the active rent contracts which belongs to the rent of code
     *
     * @param code
     * @return collection of rent contract
     */
    Collection<MBRentContractData> getContractsByRentCode(String code);

    /**
     * Get all the active rental contract by client
     *
     * @param code
     * @return collection of rent contract
     */
    Collection<MBRentContractData> getContractsByClientCode(String code);

    /**
     * Adds a rental contract or updates it if it exists
     *
     * @param contract
     */
    void updateContract(MBRentContractData contract);

    /**
     * Sets deletes the contract by setting active to false
     *
     * @param codes
     */
    void deleteContract(Set<String> codes);


    /**
     * Generates today's rent orders
     *
     * @return collection of the contracts whose orders are created
     */
    Collection<MBRentContractData> generateOrders();

    /**
     * Get Facility Rent Contracts in a range
     *
     * @param facility
     * @param range
     * @param page
     *
     * @return page of contracts
     */
    MBPageData<MBRentContractData> getFacilityContracts(String facility, MBDateRangeData range, MBPageableData page);
}