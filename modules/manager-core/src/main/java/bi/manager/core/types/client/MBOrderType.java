package bi.manager.core.types.client;

import bi.uburaro.core.types.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"client"})
@ToString(callSuper = true, exclude = {"client"})
@NoArgsConstructor
@Entity(name = MBOrderType.ITEM_TYPE)
public class MBOrderType extends ItemType {
    public static final String ITEM_TYPE = "mBOrderType";
    public static final String CLIENT = "client";
    public static final String QUANTITY = "orderDate";
    public static final String UNIT = "unit";
    public static final String ORDER_DATE = "orderDate";
    public static final String COST = "cost";

    @OneToOne(cascade = CascadeType.ALL)
    private MBClientType client;

    private Integer quantity;
    private Integer unit;
    private LocalDate orderDate;
    private Long cost;
}
