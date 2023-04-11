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
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBRentOrderType.class.equals(typeClass);
    }

    @Query("SELECT c FROM "+ MBRentOrderType.ITEM_TYPE+" AS c WHERE c."+MBRentOrderType.RENT_PROPERTY+".facility.code = ?1 AND (c."+MBRentOrderType.FROM+" <= ?3 AND c."+MBRentOrderType.TO+" >= ?2)")
    Page<MBRentOrderType> findOrdersByFacilityAndDates(String facility, LocalDate from, LocalDate to, Pageable pageable);

    @Query("SELECT c FROM "+ MBRentOrderType.ITEM_TYPE+" AS c WHERE c."+MBRentOrderType.RENT_PROPERTY+".facility.code = ?1 AND (c."+MBRentOrderType.FROM+" <= ?3 AND c."+MBRentOrderType.TO+" >= ?2)")
    Collection<MBRentOrderType> findOrdersByFacilityAndDates(String facility, LocalDate from, LocalDate to);
}
