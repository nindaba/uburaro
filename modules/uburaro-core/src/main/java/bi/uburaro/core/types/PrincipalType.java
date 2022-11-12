package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.BranchGroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = PrincipalType.ITEM_TYPE)
public class PrincipalType extends ItemType{
    public static final String ITEM_TYPE = "principal";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String BRANCH_GROUPS = "branchGroups";


    private String username;
    private String password;
    
    @ManyToMany
    private Set<BranchGroupType> branchGroups;
}
