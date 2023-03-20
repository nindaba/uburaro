package bi.manager.core.repositories;

import bi.manager.core.types.client.MBInvoiceType;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;

public interface MBInvoiceRepository extends ItemRepository<MBInvoiceType> {
    MBInvoiceType findByInvoiceNumber(String invoiceNumber);

    @Query("select i from mBInvoice as i where i.client.facility.code = ?1 and i.dateModified between ?2 and ?3")
    Collection<MBInvoiceType> findInvoiceReport(String facility, Date from, Date to);

    @Override
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass) {
        return MBInvoiceType.class.equals(typeClass);
    }
}
