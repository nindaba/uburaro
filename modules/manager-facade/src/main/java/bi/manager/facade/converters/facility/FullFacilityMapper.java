package bi.manager.facade.converters.facility;

import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.facade.converters.category.CategoryMapper;
import bi.manager.facade.converters.client.ClientMapper;
import bi.manager.facade.data.MBFacilityData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = {FullCapitalMapper.class, CategoryMapper.class, ClientMapper.class})
public interface FullFacilityMapper {
    Collection<MBFacilityData> facilitiesToData(Collection<MBFacilityType> facilities);


    @Mapping(target = "dateCreated", expression = "java(new java.util.Date())")
    @Mapping(target = "modificationLogs",ignore = true)
    @Mapping(target = MBFacilityType.CLIENTS,ignore = true)
    MBFacilityData facilityToData(MBFacilityType facility);

    @InheritInverseConfiguration
    Collection<MBFacilityType> facilitiesToType(Collection<MBFacilityData> facilities);

    @InheritInverseConfiguration
    MBFacilityType facilityToType(MBFacilityData facility);

}
