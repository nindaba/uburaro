package bi.manager.core.repositories;

import bi.manager.core.types.client.MBRentContractType;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;

public interface MBRentContractRepository extends ItemRepository<MBRentContractType> {

    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBRentContractType.class.equals(typeClass);
    }

    Collection<MBRentContractType> findMBRentContractTypesByNextOrderDateBefore(LocalDate date);

    @Query("SELECT c FROM "+MBRentContractType.ITEM_TYPE+" AS c WHERE c."+MBRentContractType.RENT_PROPERTY+".facility.code = ?1 AND (c."+MBRentContractType.FROM+" <= ?3 OR c."+MBRentContractType.TO+" >= ?2)")
    Page<MBRentContractType> findContractsByFacilityAndDates(String facility, LocalDate from, LocalDate to, Pageable pageable);
}
