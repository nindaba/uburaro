package bi.uburaro.core.types.groups;

import bi.uburaro.core.types.RoomType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true, exclude = {"rooms"})
@NoArgsConstructor
@Entity(name = PriceGroupType.ITEM_TYPE)
public class PriceGroupType extends GroupType {
    public static final String ITEM_TYPE = "priceGroup";
    public static final String PRICE_VALUE = "priceValue";
    public static final String ROOMS = "rooms";

    private Double priceValue;

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
    private Set<RoomType> rooms =  new HashSet<>();

}
