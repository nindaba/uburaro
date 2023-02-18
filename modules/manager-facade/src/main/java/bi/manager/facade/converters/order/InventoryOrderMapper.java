package bi.manager.facade.converters.order;

import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.facade.data.MBInventoryOrderData;
import bi.uburaro.core.types.ItemType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface InventoryOrderMapper {
    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "clientCode", source = "client.code")
    @Mapping(target = "itemName", source = "inventory.name")
    @Mapping(target = "itemCode", source = "inventory.code")
    MBInventoryOrderData inventoryOrderToData(MBInventoryOrderType inventoryOrder);

    @InheritInverseConfiguration
    MBInventoryOrderType inventoryOrderToType(MBInventoryOrderData inventoryOrder);

    Collection<MBInventoryOrderData> inventoriesToData(Collection<MBInventoryOrderType> inventories);
}
