package bi.uburaro.core.repositories;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.MBInventoryOrderType;
public interface MBInventoryOrderRepository extends ItemRepository<MBInventoryOrderType>{
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBInventoryOrderType.class.equals(typeClass);
    }
}
