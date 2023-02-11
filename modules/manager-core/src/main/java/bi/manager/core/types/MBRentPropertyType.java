package bi.manager.core.types;

import bi.manager.core.types.client.MBClientType;
import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.client.MBRentOrderType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "rentOrders"
})
@NoArgsConstructor
@Entity(name = MBRentPropertyType.ITEM_TYPE)
public class MBRentPropertyType extends ItemType {
    public static final String ITEM_TYPE = "mBRentProperty";
    public static final String NAME = "name";
    public static final String COST = "cost";
    public static final String RENT_ORDERS = "rentOrders";
    public static final String CURRENT_CLIENT = "currentClient";
    public static final String UNIT = "unit";

    @OneToMany(cascade = CascadeType.ALL)
    private Set<MBRentOrderType> rentOrders =  new HashSet<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private MBClientType currentClient;

    private String name;
    private Integer unit;
    private Long cost;


}
