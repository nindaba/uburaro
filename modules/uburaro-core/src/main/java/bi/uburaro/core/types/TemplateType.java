package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.GroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class TemplateType extends ItemType{
    public static final String ITEM_TYPE = "room";

    private String roomNumber;
    private Set<GroupType> groups;

}
