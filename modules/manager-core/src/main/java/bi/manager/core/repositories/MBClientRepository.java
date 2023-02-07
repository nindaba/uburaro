package bi.manager.core.repositories;

import bi.manager.core.types.client.MBClientType;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;

public interface MBClientRepository extends ItemRepository<MBClientType> {
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBClientType.class.equals(typeClass);
    }
}
