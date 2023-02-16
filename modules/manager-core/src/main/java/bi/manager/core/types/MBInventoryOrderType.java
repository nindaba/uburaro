package bi.manager.core.types;

import bi.manager.core.types.client.MBOrderType;
import bi.manager.core.types.enums.MBInventoryEntryEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"inventory", "orderEntry"})
@ToString(callSuper = true,exclude = {"inventory", "orderEntry"})
@NoArgsConstructor
@Entity(name = MBInventoryOrderType.ITEM_TYPE)
public class MBInventoryOrderType extends MBOrderType {
    public static final String ITEM_TYPE = "mBInventoryOrder";
    public static final String ORDER_NUMBER = "orderNumber";
    public static final String ORDER_ENTRY = "orderEntry";
    public static final String INVENTORY = "inventory";

    private String orderNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    private MBInventoryType inventory;

    private MBInventoryEntryEnum orderEntry;
}
