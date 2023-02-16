package bi.manager.facade.facades;

import bi.manager.facade.data.MBClientData;

import java.util.Collection;
import java.util.Set;

public interface MBClientFacade {

    /**
     * Get all the clients who belong to the {@code facilityCode} and are active
     *
     * @param allFields
     * @param facilityCode
     * @return collection of clients
     */
    Collection<MBClientData> getClientsByFacilityCode(String facilityCode, boolean allFields);

    /**
     * Gets a client by {@code code}
     *
     * @param allFields
     * @param code
     * @return client
     */
    MBClientData getClientByCode(String code, boolean allFields);

    /**
     * Deletes all the clients who belong to {@code  codes} s
     * imply by setting inactivating them {@code client.setActive(false)}
     *
     * @param codes
     */
    void deleteClients(Set<String> codes);

    /**
     * Update or create a client,
     * if the client is found already, an update will take place
     * but if the client code is not registered, it will update the client
     *
     */
    void updateClient(MBClientData client);
}
