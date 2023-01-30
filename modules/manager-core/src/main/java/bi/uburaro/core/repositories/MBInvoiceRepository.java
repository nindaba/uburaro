package bi.uburaro.core.repositories;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.client.MBInvoiceType;

public interface MBInvoiceRepository extends ItemRepository<MBInvoiceType>{
    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBInvoiceType.class.equals(typeClass);
    }
}
