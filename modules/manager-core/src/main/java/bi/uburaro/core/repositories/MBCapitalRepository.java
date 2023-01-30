package bi.uburaro.core.repositories;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.MBCapitalType;

public interface MBCapitalRepository extends ItemRepository<MBCapitalType>{
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBCapitalType.class.equals(typeClass);
    }
}
