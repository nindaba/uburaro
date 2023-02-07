package bi.manager.core.repositories;

import bi.manager.core.types.MBCategoryType;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;

public interface MBCategoryRepository extends ItemRepository<MBCategoryType> {
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBCategoryType.class.equals(typeClass);
    }
}
