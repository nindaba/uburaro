package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.EmployeeType;
import bi.uburaro.facade.data.EmployeeData;
import bi.uburaro.facade.pupulators.Populator;

public class BasicEmployeePopulator implements Populator<EmployeeType, EmployeeData> {
    @Override
    public void populate(final EmployeeType source, final EmployeeData target) {
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setUsername(source.getUsername());
    }
}
