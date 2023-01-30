package bi.uburaro.core.repositories;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.client.MBClientType;

public interface MBClientRepository extends ItemRepository<MBClientType>{
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBClientType.class.equals(typeClass);
    }
}
