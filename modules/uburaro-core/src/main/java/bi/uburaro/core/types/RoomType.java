package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.PriceGroupType;
import bi.uburaro.core.types.groups.RoomGroupType;
import bi.uburaro.core.types.groups.TaxGroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = RoomType.ITEM_TYPE)
public class RoomType extends ItemType{
    public static final String ITEM_TYPE = "room";
    public static final String ROOM_NUMBER = "roomNumber";
    public static final String GROUPS = "groups";
    public static final String PRICE_GROUPS = "priceGroups";

    private String roomNumber;
    @ManyToMany
    private Set<RoomGroupType> roomGroups;
    @ManyToMany
    private Set<PriceGroupType> priceGroups;
    @ManyToMany
    private Set<TaxGroupType> taxGroups;

}
