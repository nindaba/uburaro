package bi.manager.facade.converters.client;

import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBRentContractType;
import bi.manager.facade.converters.order.RentOrderMapper;
import bi.manager.facade.data.MBRentContractData;
import bi.manager.facade.data.NamedItemData;
import bi.uburaro.core.types.ItemType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = {RentOrderMapper.class})
public interface RentContractMapper {
    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    @Mapping(target = "property", source = MBRentContractType.RENT_PROPERTY)
    MBRentContractData contractToData(MBRentContractType client);

    default NamedItemData clientToData(MBClientType source) {
        NamedItemData target = new NamedItemData();
        target.setName(source.getName());
        target.setCode(source.getCode());
        return target;
    }

    default NamedItemData propertyToData(MBRentPropertyType source) {
        NamedItemData target = new NamedItemData();
        target.setName(source.getName());
        target.setCode(source.getCode());
        return target;
    }

    @InheritInverseConfiguration
    MBRentContractType contractToType(MBRentContractData client);

    Collection<MBRentContractData> contractsToData(Collection<MBRentContractType> clients);

}
