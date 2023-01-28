package bi.uburaro.core.types.groups;

import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.PrincipalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"principals", "members"})
@NoArgsConstructor
@Entity(name = BranchGroupType.ITEM_TYPE)
public class BranchGroupType extends GroupType {
    public static final String ITEM_TYPE = "branchGroup";
    public static final String MEMBERS = "members";
    public static final String EMPLOYEES = "employees";
    public static final String PRINCIPALS = "principals";

    @OneToMany
    private Set<HotelType> members = new HashSet<>();

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
    private Set<PrincipalType> principals = new HashSet<>();
}
