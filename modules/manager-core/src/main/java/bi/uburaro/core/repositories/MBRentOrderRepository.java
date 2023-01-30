package bi.uburaro.core.repositories;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.client.MBRentOrderType;
public interface MBRentOrderRepository extends ItemRepository<MBRentOrderType>{
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBRentOrderType.class.equals(typeClass);
    }
}
