package bi.manager.facade.converters.client;

import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.facade.converters.order.OrderMapper;
import bi.manager.facade.converters.order.RentOrderMapper;
import bi.manager.facade.data.MBInvoiceData;
import bi.manager.facade.data.NamedItemData;
import bi.uburaro.core.types.ItemType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface InvoiceMapper extends ClientNamedMapper{

    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    MBInvoiceData invoiceToDate(MBInvoiceType invoice);

    @InheritInverseConfiguration
    MBInvoiceType invoiceToType(MBInvoiceData invoice);

    Collection<MBInvoiceData> invoicesToData(Collection<MBInvoiceType> invoices);

}
