package bi.uburaro.core.repositories;

import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.PrincipalType;

public interface PrincipleRepository extends ItemRepository<PrincipalType> {
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        //for this method to work for the repository resolver
        //you need to put this repository to the list of supported repositories
        return typeClass != null
                && PrincipalType.class.isAssignableFrom(typeClass);
    }
}
