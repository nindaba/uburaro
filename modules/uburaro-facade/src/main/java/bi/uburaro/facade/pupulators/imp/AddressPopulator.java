package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.AddressType;
import bi.uburaro.facade.pupulators.Populator;
import bi.uburaro.facade.data.AddressData;

public class AddressPopulator implements Populator<AddressType, AddressData> {
    @Override
    public void populate(AddressType source, AddressData target) {
        target.setCountry(source.getCountry());
        target.setDistrict(source.getDistrict());
        target.setHouseNumber(source.getHouseNumber());
        target.setProvince(source.getProvince());
        target.setPostCode(source.getPostCode());
        target.setStreet(source.getStreet());
    }
}
