package bi.manager.core.services.impl;

import java.util.Set;

public interface MBOrderService {

    /**
     * Delete order, this will revert the quantity to the inventory and then deletes
     *
     * @param orderNumber
     */
    void deleteOrder(Set<String> orderNumber);
}
