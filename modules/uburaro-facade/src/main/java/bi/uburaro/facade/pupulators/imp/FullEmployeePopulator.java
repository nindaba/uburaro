package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.EmployeeType;
import bi.uburaro.core.types.groups.GroupType;
import bi.uburaro.facade.data.EmployeeData;
import bi.uburaro.facade.pupulators.Populator;

import java.util.stream.Collectors;

public class FullEmployeePopulator implements Populator<EmployeeType, EmployeeData> {


    @Override
    public void populate(final EmployeeType source, final EmployeeData target) {
        target.setRoles(source.getEmployeeGroups().stream()
                .map(GroupType::getCode)
                .collect(Collectors.toList()));
    }
}
