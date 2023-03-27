package bi.manager.core.repositories;

import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.enums.MBInventoryEntryEnum;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.client.MBOrderType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface MBOrderRepository extends ItemRepository<MBOrderType> {

    /**
     * Deletes an order by orderNumber
     *
     * @param orderNumber
     */
    void deleteByOrderNumber(String orderNumber);

    /**
     *Finds an order by its orderNumber
     *
     * @param orderNumber
     */
    MBOrderType findByOrderNumber(String orderNumber);


    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBOrderType.class.equals(typeClass);
    }
}
