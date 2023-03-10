package bi.manager.core.repositories;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

public interface MBCapitalEntryRepository extends ItemRepository<MBCapitalEntryType> {
    Collection<MBCapitalEntryType> findAllByCapitalAndDateModifiedBetween(MBCapitalType capital, Date from, Date to);
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBCapitalEntryType.class.equals(typeClass);
    }
}
