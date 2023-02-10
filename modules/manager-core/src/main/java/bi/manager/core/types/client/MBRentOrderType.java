package bi.manager.core.types.client;

import bi.manager.core.types.MBRentPropertyType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = MBRentOrderType.ITEM_TYPE)
public class MBRentOrderType extends MBOrderType {
    public static final String ITEM_TYPE = "mBRentOrder";
    public static final String ORDER_NUMBER = "orderNumber";
    public static final String ITEM = "item";

    private String orderNumber;

    @OneToOne
    private MBRentPropertyType item;
}