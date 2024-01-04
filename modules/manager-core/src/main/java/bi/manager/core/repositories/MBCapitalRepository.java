package bi.manager.core.repositories;

import bi.manager.core.types.MBCapitalType;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;

public interface MBCapitalRepository extends ItemRepository<MBCapitalType> {
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBCapitalType.class.equals(typeClass);
    }
}
