package bi.uburaro.core.types.groups;

import bi.uburaro.core.types.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class GroupType extends ItemType {
    public static final String ITEM_TYPE = "hotelUserGroup";
    public static final String GROUP_NAME = "groupName";

    private String groupName;
}
