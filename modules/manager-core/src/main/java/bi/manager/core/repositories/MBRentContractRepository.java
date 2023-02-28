package bi.manager.core.repositories;

import bi.manager.core.types.client.MBRentContractType;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;

public interface MBRentContractRepository extends ItemRepository<MBRentContractType> {
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBRentContractType.class.equals(typeClass);
    }
}
