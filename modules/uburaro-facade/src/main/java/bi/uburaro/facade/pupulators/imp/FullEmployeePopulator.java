package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.EmployeeType;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.groups.GroupType;
import bi.uburaro.facade.data.EmployeeData;
import bi.uburaro.facade.pupulators.Populator;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.stream.Collectors;

public class FullEmployeePopulator implements Populator<EmployeeType, EmployeeData> {
    @Override
    public void populate(final EmployeeType source, final EmployeeData target) {
        target.setAuthorities(source.getEmployeeGroups().stream()
                .filter(ItemType::isActive)
                .map(GroupType::getGroupName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }
}
