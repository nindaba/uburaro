package bi.manager.core.repositories;

import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.MBRentPropertyType;

public interface MBRentPropertyRepository extends ItemRepository<MBRentPropertyType> {
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBRentPropertyRepository.class.equals(typeClass);
    }
}
