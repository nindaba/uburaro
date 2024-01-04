package bi.manager.facade.converters.facility;

import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.facade.data.MBCapitalData;
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
    @Mapping(target = "capital")
    @Mapping(target = "modificationLogs", ignore = true)
    @Mapping(target = "dateCreated", source = "dateModified")
    MBFacilityData facilityToData(MBFacilityType facility);

    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "entries", ignore = true)
    @Mapping(target = "modificationLogs",ignore = true)
    MBCapitalData capitalToData(MBCapitalType capitalType);
    @InheritInverseConfiguration
    Collection<MBFacilityType> facilitiesToType(Collection<MBFacilityData> facilities);
    @InheritInverseConfiguration
    MBFacilityType facilityToType(MBFacilityData facility);
}
