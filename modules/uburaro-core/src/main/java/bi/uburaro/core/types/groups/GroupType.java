package bi.uburaro.core.types.groups;

import bi.uburaro.core.types.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@MappedSuperclass
public class GroupType extends ItemType {
    public static final String ITEM_TYPE = "group";
    public static final String GROUP_NAME = "groupName";

    private String groupName;
}
