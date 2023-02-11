package bi.manager.core.types.client;

import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.enums.MBPaymentModeEnum;
import bi.manager.core.types.enums.MBServiceEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = MBInvoiceType.ITEM_TYPE)
public class MBInvoiceType extends ItemType {
    public static final String ITEM_TYPE = "mBInvoice";
    public static final String INVOICE_NUMBER = "invoiceNumber";
    public static final String SERVICE = "service";
    public static final String PAYMENT_MODE = "paymentMode";
    public static final String DESCRIPTION = "description";

    public static final String CLIENT = "client";
    public static final String QUANTITY = "orderDate";
    public static final String UNIT = "unit";
    public static final String ORDER_DATE = "orderDate";
    public static final String COST = "cost";
    public static final String ORDER = "order";

    @OneToOne(cascade = CascadeType.ALL)
    private MBClientType client;
    @OneToOne(cascade = CascadeType.ALL)
    private MBOrderType order;

    private Integer quantity;
    private Integer unit;
    private LocalDate orderDate;
    private Long cost;

    private String invoiceNumber;
    private MBServiceEnum service;
    private MBPaymentModeEnum paymentMode;
    private String description;
}
