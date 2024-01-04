package bi.manager.core.repositories;

import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.MBInventoryType;

public interface MBInventoryRepository extends ItemRepository<MBInventoryType> {
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBInventoryType.class.equals(typeClass);
    }
}
