package bi.uburaro.core.repositories;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.MBCategoryType;

public interface MBCategoryRepository extends ItemRepository<MBCategoryType>{
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBCategoryType.class.equals(typeClass);
    }
}
