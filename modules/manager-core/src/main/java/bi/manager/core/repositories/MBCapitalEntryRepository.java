package bi.manager.core.repositories;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;

public interface MBCapitalEntryRepository extends ItemRepository<MBCapitalEntryType> {
    String QUERY = "SELECT c FROM " +MBCapitalEntryType.ITEM_TYPE+" AS c"+
            " WHERE "+MBCapitalEntryType.CAPITAL+".facility.code = ?1" +
            " AND "+MBCapitalEntryType.DATE_MODIFIED+" BETWEEN ?2 AND ?3";
    @Query(QUERY)
    Collection<MBCapitalEntryType> findAllByCapitalAndDateModifiedBetween(String facility, Date from, Date to);
    @Query(QUERY)
    Page<MBCapitalEntryType> findAllByCapitalAndDateModifiedBetween(String facility, Date from, Date to, Pageable pageable);

    @Query(QUERY+" AND "+MBCapitalEntryType.ENTRY_TYPE +" = ?4")
    Collection<MBCapitalEntryType> findAllByFacilityAndDateModifiedBetween(String facility, Date from, Date to, MBEntryEnum entryType);

    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBCapitalEntryType.class.equals(typeClass);
    }
}
