package bi.manager.core.repositories;

import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.MBInventoryOrderType;
public interface MBInventoryOrderRepository extends ItemRepository<MBInventoryOrderType> {
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBInventoryOrderType.class.equals(typeClass);
    }
}
