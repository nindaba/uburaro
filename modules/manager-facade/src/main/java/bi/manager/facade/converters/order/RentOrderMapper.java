package bi.manager.facade.converters.order;

import bi.manager.core.types.client.MBRentOrderType;
import bi.manager.facade.data.MBRentOrderData;
import bi.uburaro.core.types.ItemType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface RentOrderMapper {
    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "clientCode", source = "client.code")
    @Mapping(target = "itemName", source = "rentProperty.name")
    @Mapping(target = "itemCode", source = "rentProperty.code")
    MBRentOrderData rentOrderToData(MBRentOrderType inventoryOrder);


    @InheritInverseConfiguration
    MBRentOrderType rentOrderToType(MBRentOrderData inventoryOrder);

    Collection<MBRentOrderData> rentsToData(Collection<MBRentOrderType> inventories);
}
