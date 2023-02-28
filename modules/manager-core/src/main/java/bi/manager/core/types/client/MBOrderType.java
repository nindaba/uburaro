package bi.manager.core.types.client;

import bi.uburaro.core.types.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"client","invoice"})
@ToString(callSuper = true, exclude = {"client","invoice"})
@NoArgsConstructor
@Entity(name = MBOrderType.ITEM_TYPE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class MBOrderType extends ItemType {
    public static final String ITEM_TYPE = "mBOrderType";
    public static final String CLIENT = "client";
    public static final String QUANTITY = "orderDate";
    public static final String UNIT = "unit";
    public static final String ORDER_DATE = "orderDate";
    public static final String COST = "cost";
    public static final String PAID = "paid";
    public static final String ORDER_NUMBER = "orderNumber";
    public static final String INVOICE = "invoice";


    @ManyToOne(cascade = CascadeType.ALL)
    private MBClientType client;

    @ManyToOne(cascade = CascadeType.ALL)
    private MBInvoiceType invoice;

    private String orderNumber;
    private int quantity;
    private int unit;
    private LocalDate orderDate;
    private long cost;
    private boolean paid;
}
