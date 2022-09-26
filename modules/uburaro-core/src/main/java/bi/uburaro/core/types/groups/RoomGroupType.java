package bi.uburaro.core.types.groups;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class RoomGroupType extends GroupType {
    public static final String ITEM_TYPE = "roomGroup";
}
