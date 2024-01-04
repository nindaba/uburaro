package bi.manager.core.services;

import bi.manager.core.types.client.MBClientType;

import java.util.Collection;
import java.util.Set;

public interface MBClientService extends MBTypeService<MBClientType>{
    /**
     * Gets all the clients that belong to the facility {@code  facilityCode} who are active
     *
     * @param facilityCode
     * @return collection of categories
     */
    Collection<MBClientType> getClientsByFacilityCode(String facilityCode);

    /**
     * Gets a client with {@code code}
     *
     * @param code
     * @return client
     */
    MBClientType getClientByCode(String code);

    /**
     * Finds a client with {@code client.code} if found client is updated,
     * if the client is not found a new will be created
     *
     * @param client
     */
    void updateClient(MBClientType client);

    /**
     * Gets all the clients with totalDebt less than 0 who belong {@code  facility}
     *
     * @param facility
     * @return collection of clients
     */
    Collection<MBClientType> getClientsWithDebt(String facility);
}
