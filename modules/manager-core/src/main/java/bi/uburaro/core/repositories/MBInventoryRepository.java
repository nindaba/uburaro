package bi.uburaro.core.repositories;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.MBInventoryType;

public interface MBInventoryRepository extends ItemRepository<MBInventoryType>{
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBInventoryType.class.equals(typeClass);
    }
}
