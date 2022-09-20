package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.GroupType;
import bi.uburaro.core.types.groups.PriceGroupType;
import bi.uburaro.core.types.groups.TaxGroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class RoomType extends ItemType{
    public static final String ITEM_TYPE = "room";
    public static final String ROOM_NUMBER = "roomNumber";
    public static final String GROUPS = "groups";
    public static final String PRICE_GROUPS = "priceGroups";

    private String roomNumber;
    private Set<GroupType> groups;
    private Set<PriceGroupType> priceGroups;
    private Set<TaxGroupType> taxGroups;

}
