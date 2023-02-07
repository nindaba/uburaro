package bi.manager.facade.converters.facility;

import bi.manager.core.types.MBFacilityType;
import bi.manager.facade.data.MBFacilityData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface FacilityMapper {

    Collection<MBFacilityData> facilitiesToData(Collection<MBFacilityType> facilities);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "capital", ignore = true)
    @Mapping(target = "modificationLogs", ignore = true)
    @Mapping(target = "dateCreated", expression = "java(new java.util.Date())")
    MBFacilityData facilityToData(MBFacilityType facility);

    @InheritInverseConfiguration
    Collection<MBFacilityType> facilitiesToType(Collection<MBFacilityData> facilities);
    @InheritInverseConfiguration
    MBFacilityType facilityToType(MBFacilityData facility);
}
