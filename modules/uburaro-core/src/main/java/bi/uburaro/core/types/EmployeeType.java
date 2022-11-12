package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.EmployeeGroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = EmployeeType.ITEM_TYPE)
public class EmployeeType extends PrincipalType {
    public static final String ITEM_TYPE = "employee";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMPLOYEE_GROUPS = "employeeGroups";

    private String firstName;
    private String lastName;
    @ManyToMany
    private Set<EmployeeGroupType> employeeGroups;
}
