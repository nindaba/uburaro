package bi.manager.facade.converters.facility;

import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBInventoryType;
import bi.manager.facade.data.MBCategoryData;
import bi.manager.facade.data.MBInventoryData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface CategoryInventoryMapper {

    @Mapping(target = "modificationLogs", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = MBCategoryType.FACILITY, ignore = true)
    MBCategoryData categoryToData(MBCategoryType category);

    default MBInventoryData inventoryToData(final MBInventoryType source){
        final MBInventoryData target = new MBInventoryData();
        target.setQuantity(source.getQuantity());
        target.setCost(source.getCost());
        return target;
    }


    @InheritInverseConfiguration(name = "categoryToData")
    MBCategoryType categoryToType(MBCategoryData category);

    Collection<MBCategoryData> categoriesToData(Collection<MBCategoryType> categories);
}
