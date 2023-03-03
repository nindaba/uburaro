package bi.manager.core.repositories;

import bi.manager.core.types.client.MBRentContractType;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;

import java.time.LocalDate;
import java.util.List;

public interface MBRentContractRepository extends ItemRepository<MBRentContractType> {

    List<MBRentContractType> findMBRentContractTypesByNextOrderDateAfter(LocalDate date);
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBRentContractType.class.equals(typeClass);
    }
}
