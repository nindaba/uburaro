package bi.manager.facade.converters.order;

import bi.manager.core.types.client.MBOrderType;
import bi.manager.facade.data.MBOrderData;
import bi.uburaro.core.types.ItemType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = ItemType.MODIFICATION_LOGS, ignore = true)
    MBOrderData orderToData(MBOrderType order);

    @InheritInverseConfiguration
    MBOrderType orderToType(MBOrderData order);

    @InheritInverseConfiguration
    Collection<MBOrderType> ordersToType(Collection<MBOrderData> orders);
    Collection<MBOrderData> ordersToData(Collection<MBOrderType> orders);
}
