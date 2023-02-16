package bi.manager.facade.converters.client;

import bi.manager.core.types.client.MBClientType;
import bi.manager.facade.converters.facility.FacilityMapper;
import bi.manager.facade.data.MBClientData;
import bi.uburaro.core.types.ItemType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = {FacilityMapper.class})
public interface FullClientMapper {
    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    @Mapping(target = ItemType.DATE_CREATED, ignore = true)
    @Mapping(target = MBClientType.INVOICES, ignore = true)
    @Mapping(target = MBClientType.RENT_ORDERS, ignore = true)
    @Mapping(target = MBClientType.INVENTORY_ORDERS, ignore = true)
    MBClientData clientToData(MBClientType client);


    @InheritInverseConfiguration(name = "clientToData")
    MBClientType clientToType(MBClientData client);

    Collection<MBClientData> clientsToData(Collection<MBClientType> clients);

}
