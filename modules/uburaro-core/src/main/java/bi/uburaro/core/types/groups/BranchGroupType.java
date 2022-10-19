package bi.uburaro.core.types.groups;

import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = BranchGroupType.ITEM_TYPE)
public class BranchGroupType extends GroupType {
    public static final String ITEM_TYPE = "branchGroup";
    public static final String MEMBERS = "members";

    @OneToMany
    private Set<HotelType> members;
}
