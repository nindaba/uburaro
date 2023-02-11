package bi.manager.facade.converters.category;

import bi.manager.core.types.MBCategoryType;
import bi.manager.facade.data.MBCategoryData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "modificationLogs", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "inventories", ignore = true)
    MBCategoryData categoryToData(MBCategoryType category);


    @InheritInverseConfiguration(name = "categoryToData")
    MBCategoryType categoryToType(MBCategoryData category);

    Collection<MBCategoryData> categoriesToData(Collection<MBCategoryType> categories);
}
