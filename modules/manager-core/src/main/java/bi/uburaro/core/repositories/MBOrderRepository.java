package bi.uburaro.core.repositories;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.client.MBOrderType;

public interface MBOrderRepository extends ItemRepository<MBOrderType>{
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBOrderType.class.equals(typeClass);
    }
}
