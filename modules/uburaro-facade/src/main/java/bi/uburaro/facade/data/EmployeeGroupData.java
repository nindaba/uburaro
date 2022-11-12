package bi.uburaro.facade.data;

import bi.uburaro.facade.data.groups.GroupData;

import java.util.Collection;

public class EmployeeGroupData extends GroupData {
    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    private Collection<String> roles;

}
