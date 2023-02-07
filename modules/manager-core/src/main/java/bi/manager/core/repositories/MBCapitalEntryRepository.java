package bi.manager.core.repositories;

import bi.manager.core.types.MBCapitalEntryType;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;

public interface MBCapitalEntryRepository extends ItemRepository<MBCapitalEntryType> {
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBCapitalEntryType.class.equals(typeClass);
    }
}
