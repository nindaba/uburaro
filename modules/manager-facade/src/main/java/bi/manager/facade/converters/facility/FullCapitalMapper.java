package bi.manager.facade.converters.facility;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.facade.data.MBCapitalData;
import bi.manager.facade.data.MBCapitalEntryData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface FullCapitalMapper {
    Collection<MBCapitalEntryData> capitalEntriesToData(Collection<MBCapitalEntryType> entries);

    @Mapping(target = "dateCreated", expression = "java(new java.util.Date())")
    @Mapping(target = "modificationLogs",ignore = true)
    MBCapitalData capitalToData(MBCapitalType capitalType);

    @Mapping(target = "dateCreated", expression = "java(new java.util.Date())")
    @Mapping(target = "modificationLogs",ignore = true)
    MBCapitalEntryData capitalEntryToData(MBCapitalEntryType entryType);


    @InheritInverseConfiguration
    MBCapitalType capitalToType(MBCapitalData capitalData);
}
