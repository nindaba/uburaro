package bi.manager.facade.converters.rent;

import bi.manager.core.types.MBRentPropertyType;
import bi.manager.facade.converters.client.ClientMapper;
import bi.manager.facade.data.MBRentPropertyData;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.PrimaryKeyType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring",uses = {ClientMapper.class})
public interface FullRentPropertyMapper {

    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    @Mapping(target = PrimaryKeyType.DATE_CREATED, ignore = true)
    @Mapping(target = MBRentPropertyType.RENT_ORDERS, ignore = true)
    @Mapping(target = MBRentPropertyType.FACILITY, ignore = true)
    MBRentPropertyData rentPropertyToData(MBRentPropertyType rent);


    @InheritInverseConfiguration
    MBRentPropertyType rentPropertyToType(MBRentPropertyData rent);

    Collection<MBRentPropertyData> rentPropertiesToData(Collection<MBRentPropertyType> rents);
}

