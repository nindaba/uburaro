package bi.manager.core.repositories;

import bi.manager.core.types.enums.MBInventoryEntryEnum;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.MBInventoryOrderType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface MBInventoryOrderRepository extends ItemRepository<MBInventoryOrderType> {
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBInventoryOrderType.class.equals(typeClass);
    }
    @Query("SELECT c FROM "+ MBInventoryOrderType.ITEM_TYPE+" AS c " +
            "WHERE c."+MBInventoryOrderType.INVENTORY+".category.facility.code = ?1 " +
            "AND c."+MBInventoryOrderType.ORDER_DATE+" <= ?3 " +
            "AND c."+MBInventoryOrderType.ORDER_DATE+" >= ?2 " +
            "AND c."+MBInventoryOrderType.ORDER_ENTRY+" = ?4")
    Page<MBInventoryOrderType> findAllByFacilityAndDateRange(String facility, LocalDate from, LocalDate to, MBInventoryEntryEnum orderType, Pageable pageable);
}
