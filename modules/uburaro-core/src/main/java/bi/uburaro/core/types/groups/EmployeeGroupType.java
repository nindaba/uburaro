package bi.uburaro.core.types.groups;

import bi.uburaro.core.types.EmployeeType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = EmployeeGroupType.ITEM_TYPE)
public class EmployeeGroupType extends GroupType {
    public static final String ITEM_TYPE = "employeeGroup";
    @ManyToMany
    private Set<EmployeeType> employees;
}
