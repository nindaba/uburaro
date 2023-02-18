package bi.manager.facade.converters.client;

import bi.manager.core.types.client.MBClientType;
import bi.manager.facade.data.MBClientData;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.PrimaryKeyType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    @Mapping(target = PrimaryKeyType.DATE_CREATED, ignore = true)
    @Mapping(target = MBClientType.INVOICES, ignore = true)
    @Mapping(target = MBClientType.RENT_ORDERS, ignore = true)
    @Mapping(target = MBClientType.INVENTORY_ORDERS, ignore = true)
    @Mapping(target = MBClientType.FACILITY, ignore = true)
    MBClientData clientToData(MBClientType client);


    @InheritInverseConfiguration(name = "clientToData")
    MBClientType clientToType(MBClientData client);

    Collection<MBClientData> clientsToData(Collection<MBClientType> clients);
}

