package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.EmployeeGroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"employeeGroups"})
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
    @JoinTable(name = "employee2employee_group",
            inverseJoinColumns = {
                    @JoinColumn(name = "employee_date_created"),
                    @JoinColumn(name = "employee_item_type"),
                    @JoinColumn(name = "employee_t_key")},
            joinColumns = {
                    @JoinColumn(name = "employee_groups_date_created"),
                    @JoinColumn(name = "employee_groups_item_type"),
                    @JoinColumn(name = "employee_groups_t_key")
            })
    private Set<EmployeeGroupType> employeeGroups = new HashSet<>();
}
