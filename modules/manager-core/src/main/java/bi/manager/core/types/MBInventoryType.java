package bi.manager.core.types;

import bi.uburaro.core.types.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"inventoryOrders","category"})
@ToString(callSuper = true,exclude = {"inventoryOrders","category"})
@NoArgsConstructor
@Entity(name = MBInventoryType.ITEM_TYPE)
public class MBInventoryType extends ItemType {
    public static final String ITEM_TYPE = "mBInventory";
    public static final String NAME = "name";
    public static final String CATEGORY = "category";
    public static final String QUANTITY = "quantity";
    public static final String COST = "cost";
    public static final String INVENTORY_ORDERS = "inventoryOrders";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = MBInventoryOrderType.INVENTORY)
    private Set<MBInventoryOrderType> inventoryOrders = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    private MBCategoryType category;

    private String name;
    private int quantity;
    private long cost;

}
