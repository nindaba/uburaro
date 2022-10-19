package bi.uburaro.core.repositories;

import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.ItemType;

public interface HotelRepository extends ItemRepository<HotelType> {
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return HotelType.class.equals(typeClass);
    }
}
