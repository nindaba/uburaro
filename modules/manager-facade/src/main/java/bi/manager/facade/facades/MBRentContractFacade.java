package bi.manager.facade.facades;


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
}