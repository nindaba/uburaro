package bi.manager.facade.converters.inventory;

import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.facade.data.MBInventoryOrderData;
import bi.uburaro.core.types.ItemType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface InventoryOrderMapper {
    @Mapping(target = MBInventoryOrderType.INVENTORY,ignore = true)
    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    MBInventoryOrderData inventoryOrderToData(MBInventoryOrderType inventoryOrder);


    @InheritInverseConfiguration(name = "inventoryOrderToData")
    MBInventoryOrderType inventoryOrderToType(MBInventoryOrderData inventoryOrder);

    Collection<MBInventoryOrderData> inventoriesToData(Collection<MBInventoryOrderType> inventories);
}
