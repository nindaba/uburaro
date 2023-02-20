package bi.manager.core.types.client;

import bi.manager.core.types.MBFacilityType;
import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.MBInventoryOrderType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "rentOrders",
        "inventoryOrders",
        "invoices",
        "facility",
        "inventoryOrders"
})
@ToString(callSuper = true, exclude = {
        "rentOrders",
        "inventoryOrders",
        "invoices",
        "facility",
        "inventoryOrders"
})
@NoArgsConstructor
@Entity(name = MBClientType.ITEM_TYPE)
public class MBClientType extends ItemType {
    public static final String ITEM_TYPE = "mBClient";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String INVOICES = "invoices";
    public static final String RENT_ORDERS = "rentOrders";
    public static final String INVENTORY_ORDERS = "inventoryOrders";
    public static final String TOTAL_DEBT = "totalDebt";
    public static final String FACILITY = "facility";


    private String name;
    private String address;
    private long totalDebt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = MBRentOrderType.CLIENT)
    private Set<MBRentOrderType> rentOrders = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = MBInventoryOrderType.CLIENT)
    private Set<MBInventoryOrderType> inventoryOrders = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<MBInvoiceType> invoices = new HashSet<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private MBFacilityType facility;
}
