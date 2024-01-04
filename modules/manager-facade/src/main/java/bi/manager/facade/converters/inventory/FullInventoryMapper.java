package bi.manager.facade.converters.inventory;

import bi.manager.core.types.MBInventoryType;
import bi.manager.facade.converters.category.CategoryMapper;
import bi.manager.facade.converters.order.InventoryOrderMapper;
import bi.manager.facade.data.MBInventoryData;
import bi.uburaro.core.types.ItemType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, InventoryOrderMapper.class})
public interface FullInventoryMapper {
    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    MBInventoryData inventoryToData(MBInventoryType inventory);


    @InheritInverseConfiguration(name = "inventoryToData")
    MBInventoryType inventoryToType(MBInventoryData inventory);

    Collection<MBInventoryData> inventoriesToData(Collection<MBInventoryType> inventories);
}
