package bi.uburaro.core.types.groups;

import bi.uburaro.core.types.EmployeeType;
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
@EqualsAndHashCode(callSuper = true, exclude = {"employees"})
@NoArgsConstructor
@Entity(name = EmployeeGroupType.ITEM_TYPE)
public class EmployeeGroupType extends GroupType {
    public static final String ITEM_TYPE = "employeeGroup";
    public static final String EMPLOYEES = "employees";

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
    private Set<EmployeeType> employees = new HashSet<>();
}
