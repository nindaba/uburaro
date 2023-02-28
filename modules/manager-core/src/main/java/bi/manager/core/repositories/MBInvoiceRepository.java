package bi.manager.core.repositories;

import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.client.MBInvoiceType;

public interface MBInvoiceRepository extends ItemRepository<MBInvoiceType> {
    MBInvoiceType findByInvoiceNumber(String invoiceNumber);

    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBInvoiceType.class.equals(typeClass);
    }
}
