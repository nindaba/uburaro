package bi.manager.core.repositories;

import bi.manager.core.types.client.MBRentContractType;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface MBRentContractRepository extends ItemRepository<MBRentContractType> {

    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBRentContractType.class.equals(typeClass);
    }

    Collection<MBRentContractType> findMBRentContractTypesByNextOrderDateBefore(LocalDate date);
}
