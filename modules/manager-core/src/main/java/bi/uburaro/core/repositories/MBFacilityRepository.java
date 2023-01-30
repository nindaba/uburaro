package bi.uburaro.core.repositories;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.MBFacilityType;

public interface MBFacilityRepository extends ItemRepository<MBFacilityType>{
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBFacilityType.class.equals(typeClass);
    }
}
