package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.PriceGroupType;
import bi.uburaro.core.types.groups.RoomGroupType;
import bi.uburaro.core.types.groups.TaxGroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "roomGroups",
        "priceGroups",
        "taxGroups"
})
@NoArgsConstructor
@Entity(name = RoomType.ITEM_TYPE)
public class RoomType extends ItemType {
    public static final String ITEM_TYPE = "room";
    public static final String ROOM_NUMBER = "roomNumber";
    public static final String GROUPS = "groups";
    public static final String PRICE_GROUPS = "priceGroups";

    private String roomNumber;

    @ManyToMany
    @JoinTable(name = "rooms2room_groups",
            joinColumns = {
                    @JoinColumn(name = "rooms_date_created"),
                    @JoinColumn(name = "rooms_item_type"),
                    @JoinColumn(name = "rooms_t_key")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "room_groups_date_created"),
                    @JoinColumn(name = "room_groups_item_type"),
                    @JoinColumn(name = "room_groups_t_key")
            })
    private Set<RoomGroupType> roomGroups = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "rooms2room_groups",
            joinColumns = {
                    @JoinColumn(name = "rooms_date_created"),
                    @JoinColumn(name = "rooms_item_type"),
                    @JoinColumn(name = "rooms_t_key")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "price_groups_date_created"),
                    @JoinColumn(name = "price_groups_item_type"),
                    @JoinColumn(name = "price_groups_t_key")
            })
    private Set<PriceGroupType> priceGroups =  new HashSet<>();

    @ManyToMany
    @JoinTable(name = "rooms2room_groups",
            joinColumns = {
                    @JoinColumn(name = "rooms_date_created"),
                    @JoinColumn(name = "rooms_item_type"),
                    @JoinColumn(name = "rooms_t_key")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "tax_groups_date_created"),
                    @JoinColumn(name = "tax_groups_item_type"),
                    @JoinColumn(name = "tax_groups_t_key")
            })
    private Set<TaxGroupType> taxGroups =  new HashSet<>();

}
