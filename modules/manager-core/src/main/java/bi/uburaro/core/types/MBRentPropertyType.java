package bi.uburaro.core.types;

import bi.uburaro.core.types.client.MBClientType;
import bi.uburaro.core.types.client.MBRentOrderType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class MBRentPropertyType extends ItemType{
    public static final String ITEM_TYPE = "mBInventory";
    public static final String NAME = "name";
    public static final String COST = "cost";
    public static final String RENT_ORDERS = "rentOrders";
    public static final String CURRENT_CLIENT = "currentClient";
    public static final String UNIT = "unit";

    @OneToMany
    private Set<MBRentOrderType> rentOrders =  new HashSet<>();
    @ManyToOne
    private MBClientType currentClient;

    private String name;
    private Integer unit;
    private Long cost;


}
