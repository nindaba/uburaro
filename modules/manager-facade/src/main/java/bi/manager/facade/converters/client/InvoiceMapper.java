package bi.manager.facade.converters.client;

import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.facade.converters.order.OrderMapper;
import bi.manager.facade.data.MBInvoiceData;
import bi.uburaro.core.types.ItemType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface InvoiceMapper extends CodeNamedMapper {

    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    @Mapping(target = "dateCreated", source = "dateModified")
    MBInvoiceData invoiceToDate(MBInvoiceType invoice);

    @InheritInverseConfiguration
    MBInvoiceType invoiceToType(MBInvoiceData invoice);

    Collection<MBInvoiceData> invoicesToData(Collection<MBInvoiceType> invoices);

}
