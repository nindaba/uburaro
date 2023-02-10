package bi.manager.core.types;

import bi.manager.core.types.client.MBOrderType;
import bi.manager.core.types.enums.MBInventoryEntryEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = MBInventoryOrderType.ITEM_TYPE)
public class MBInventoryOrderType extends MBOrderType {
    public static final String ITEM_TYPE = "mBInventoryOrder";
    public static final String ORDER_NUMBER = "orderNumber";
    public static final String ITEM = "item";
    public static final String ORDER_ENTRY = "orderEntry";

    private String orderNumber;

    @ManyToOne
    private MBInventoryType item;

    private MBInventoryEntryEnum orderEntry;
}