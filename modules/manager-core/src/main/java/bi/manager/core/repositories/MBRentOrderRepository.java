package bi.manager.core.repositories;

import bi.manager.core.types.client.MBRentOrderType;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;

public interface MBRentOrderRepository extends ItemRepository<MBRentOrderType> {
    String QUERY = "SELECT c FROM " + MBRentOrderType.ITEM_TYPE + " AS c " +
            "WHERE c." + MBRentOrderType.RENT_PROPERTY + ".facility.code = ?1 " +
            "AND (c." + MBRentOrderType.FROM + " <= ?3 " +
            "AND c." + MBRentOrderType.TO + " >= ?2)";

    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBRentOrderType.class.equals(typeClass);
    }

    @Query(QUERY)
    Page<MBRentOrderType> findOrdersByFacilityAndDates(String facility, LocalDate from, LocalDate to, Pageable pageable);

    @Query(QUERY)
    Collection<MBRentOrderType> findOrdersByFacilityAndDates(String facility, LocalDate from, LocalDate to);
}
