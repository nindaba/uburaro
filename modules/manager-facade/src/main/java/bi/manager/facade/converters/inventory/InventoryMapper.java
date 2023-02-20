package bi.manager.facade.converters.inventory;

import bi.manager.core.types.MBInventoryType;
import bi.manager.facade.data.MBInventoryData;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.PrimaryKeyType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    @Mapping(target = PrimaryKeyType.DATE_CREATED, ignore = true)
    @Mapping(target = MBInventoryType.INVENTORY_ORDERS, ignore = true)
    @Mapping(target = MBInventoryType.CATEGORY, ignore = true)
    MBInventoryData inventoryToData(MBInventoryType inventory);


    @InheritInverseConfiguration(name = "inventoryToData")
    MBInventoryType inventoryToType(MBInventoryData inventory);

    Collection<MBInventoryData> inventoriesToData(Collection<MBInventoryType> inventories);
}
