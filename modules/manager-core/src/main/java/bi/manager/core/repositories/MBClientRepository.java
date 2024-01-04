package bi.manager.core.repositories;

import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBClientType;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface MBClientRepository extends ItemRepository<MBClientType> {
    @Query(value = "SELECT client FROM " + MBClientType.ITEM_TYPE + " AS client WHERE client." + MBClientType.FACILITY + "." + MBFacilityType.CODE + " = ?1 AND client." + MBClientType.TOTAL_DEBT + " < 0")
    Collection<MBClientType> findAllWithDebt(String facility);

    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBClientType.class.equals(typeClass);
    }
}
