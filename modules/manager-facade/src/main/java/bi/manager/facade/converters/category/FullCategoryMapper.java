package bi.manager.facade.converters.category;

import bi.manager.core.types.MBCategoryType;
import bi.manager.facade.converters.inventory.InventoryMapper;
import bi.manager.facade.data.MBCategoryData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring",uses = InventoryMapper.class)
public interface FullCategoryMapper {
    @Mapping(target = "modificationLogs", ignore = true)
    MBCategoryData categoryToData(MBCategoryType category);
}
