package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.BranchGroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = true,exclude = {"branchGroups"})
@NoArgsConstructor
@Entity(name = PrincipalType.ITEM_TYPE)
public class PrincipalType extends ItemType {
    public static final String ITEM_TYPE = "principal";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String BRANCH_GROUPS = "branchGroups";


    private String username;
    private String password;

    @ManyToMany
    @JoinTable(name = "principal2branch_groups",
            joinColumns = {
                    @JoinColumn(name = "principal_date_created"),
                    @JoinColumn(name = "principal_item_type"),
                    @JoinColumn(name = "principal_t_key")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "branch_groups_date_created"),
                    @JoinColumn(name = "branch_groups_item_type"),
                    @JoinColumn(name = "branch_groups_t_key")
            })
    private Set<BranchGroupType> branchGroups =  new HashSet<>();

    @OneToMany
    private Set<ModificationLogType> modified;
}
