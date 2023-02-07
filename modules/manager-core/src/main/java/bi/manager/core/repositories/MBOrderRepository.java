package bi.manager.core.repositories;

import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.client.MBOrderType;

public interface MBOrderRepository extends ItemRepository<MBOrderType> {
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBOrderType.class.equals(typeClass);
    }
}
