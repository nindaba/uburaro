package bi.manager.facade.converters.category;

import bi.manager.core.types.MBCategoryType;
import bi.manager.facade.converters.inventory.InventoryMapper;
import bi.manager.facade.data.MBCategoryData;
import bi.uburaro.core.types.ItemType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring",uses = InventoryMapper.class)
public interface FullCategoryMapper {
    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    @Mapping(target = MBCategoryType.FACILITY, ignore = true)
    MBCategoryData categoryToData(MBCategoryType category);

    Collection<MBCategoryData> categoriesToData(Collection<MBCategoryType> categories);
}
