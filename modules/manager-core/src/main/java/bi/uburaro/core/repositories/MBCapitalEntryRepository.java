package bi.uburaro.core.repositories;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.MBCapitalEntryType;

public interface MBCapitalEntryRepository extends ItemRepository<MBCapitalEntryType>{
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBCapitalEntryType.class.equals(typeClass);
    }
}
