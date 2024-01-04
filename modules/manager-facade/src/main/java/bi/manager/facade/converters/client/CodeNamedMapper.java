package bi.manager.facade.converters.client;

import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.facade.data.NamedItemData;

public interface CodeNamedMapper {
    default NamedItemData clientToData(MBClientType source) {
        NamedItemData target = new NamedItemData();
        target.setName(source.getName());
        target.setCode(source.getCode());
        return target;
    }

    default NamedItemData rentPropertyToData(MBRentPropertyType source) {
        NamedItemData target = new NamedItemData();
        target.setName(source.getName());
        target.setCode(source.getCode());
        return target;
    }

}
