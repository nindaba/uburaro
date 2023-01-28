package bi.uburaro.core.types.client;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.MBInventoryOrderType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "rentOrders",
        "inventoryOrders",
        "invoices"
})
@NoArgsConstructor
@Entity(name = MBClientType.ITEM_TYPE)
public class MBClientType extends ItemType {
    public static final String ITEM_TYPE = "mBClient";
    public static final String NAME = "name";
    public static final String INVOICES = "invoices";
    public static final String RENT_ORDERS = "rentOrders";
    public static final String INVENTORY_ORDERS = "inventoryOrders";

    private String name;

    @OneToMany
    private Set<MBRentOrderType> rentOrders =  new HashSet<>();
    @OneToMany
    private Set<MBInventoryOrderType> inventoryOrders =  new HashSet<>();
    @OneToMany
    private Set<MBInvoiceType> invoices = new HashSet<>();
}
