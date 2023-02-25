package bi.manager.core.types;

import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBRentContractType;
import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.client.MBRentOrderType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "rentOrders",
        "facility"
})
@ToString(callSuper = true, exclude = {
        "rentOrders",
        "facility"
})
@NoArgsConstructor
@Entity(name = MBRentPropertyType.ITEM_TYPE)
public class MBRentPropertyType extends ItemType {
    public static final String ITEM_TYPE = "mBRentProperty";
    public static final String NAME = "name";
    public static final String COST = "cost";
    public static final String RENT_ORDERS = "rentOrders";
    public static final String CURRENT_ORDER = "currentOrder";
    public static final String UNIT = "unit";
    public static final String FACILITY = "facility";
    public static final String ADDRESS = "address";
    public static final String CONTRACTS = "contracts";
    public static final String TOTAL_INCOME = "totalIncome";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = MBRentOrderType.RENT_PROPERTY)
    private Set<MBRentOrderType> rentOrders = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    private MBRentContractType currentContract;
    @ManyToOne
    private MBFacilityType facility;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = MBRentContractType.RENT_PROPERTY)
    private Set<MBRentContractType> contracts = new HashSet<>();

    private String name;
    private int unit;
    private long cost;
    private String address;
    private long totalIncome;
}
