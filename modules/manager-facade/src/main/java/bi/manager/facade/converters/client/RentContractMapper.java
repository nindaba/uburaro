package bi.manager.facade.converters.client;

import bi.manager.core.types.client.MBRentContractType;
import bi.manager.facade.converters.rent.RentPropertyMapper;
import bi.manager.facade.data.MBPageData;
import bi.manager.facade.data.MBRentContractData;
import bi.uburaro.core.types.ItemType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = {RentPropertyMapper.class})
public interface RentContractMapper extends CodeNamedMapper {
    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    @Mapping(target = "property", source = MBRentContractType.RENT_PROPERTY)
    @Mapping(target = MBRentContractType.ORDERS, ignore = true)
    @Mapping(target = "hasOrders", expression = "java(!contract.getOrders().isEmpty())")
    MBRentContractData contractToData(MBRentContractType contract);

    @InheritInverseConfiguration
    MBRentContractType contractToType(MBRentContractData contract);

    Collection<MBRentContractData> contractsToData(Collection<MBRentContractType> contracts);
}
