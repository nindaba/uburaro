package bi.manager.core.repositories;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;

public interface MBCapitalEntryRepository extends ItemRepository<MBCapitalEntryType> {
    Collection<MBCapitalEntryType> findAllByCapitalAndDateModifiedBetween(MBCapitalType capital, Date from, Date to);

    Page<MBCapitalEntryType> findAllByCapitalAndDateModifiedBetween(MBCapitalType capital, Date from, Date to, Pageable pageable);

    @Query("SELECT c FROM " +MBCapitalEntryType.ITEM_TYPE+" AS c"+
            " WHERE "+MBCapitalEntryType.CAPITAL+".facility.code = ?1" +
            " AND "+MBCapitalEntryType.DATE_MODIFIED+" BETWEEN ?2 AND ?3" +
            " AND "+MBCapitalEntryType.ENTRY_TYPE +" = ?4")
    Collection<MBCapitalEntryType> findTotalAmount(String facility, Date from, Date to, MBEntryEnum entryType);

    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBCapitalEntryType.class.equals(typeClass);
    }
}
