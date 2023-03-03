package bi.manager.core.strategies;

import bi.manager.core.types.client.MBRentContractType;

import java.util.Collection;

public interface GenerateRentOrdersStrategy {
    /**
     * Generates orders for active current rent contracts where the next order date is before today
     *
     * @return collection of contracts whose orders are generated
     */
    Collection<MBRentContractType> generateOrders();
}
