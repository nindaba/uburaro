package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.CustomerType;
import bi.uburaro.facade.data.CustomerData;
import bi.uburaro.facade.pupulators.Populator;

public class BasicCustomerPopulator implements Populator<CustomerType, CustomerData> {
    @Override
    public void populate(final CustomerType source, final CustomerData target) {
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setAge(source.getAge());
        target.setIdentity(source.getIdentity());
        target.setNationality(source.getNationality());
        target.setPhone(source.getPhone());
        target.setGender(source.getGender());
    }
}
