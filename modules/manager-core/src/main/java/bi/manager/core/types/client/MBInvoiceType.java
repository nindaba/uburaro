package bi.manager.core.types.client;

import bi.manager.core.types.MBCapitalEntryType;
import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.enums.MBPaymentModeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"client", "orders","capitalEntry"})
@ToString(callSuper = true, exclude = {"client", "orders","capitalEntry"})
@NoArgsConstructor
@Entity(name = MBInvoiceType.ITEM_TYPE)
public class MBInvoiceType extends ItemType {
    public static final String ITEM_TYPE = "mBInvoice";
    public static final String INVOICE_NUMBER = "invoiceNumber";
    public static final String PAYMENT_MODE = "paymentMode";
    public static final String DESCRIPTION = "description";

    public static final String CLIENT = "client";
    public static final String AMOUNT = "amount";
    public static final String ORDERS = "orders";
    public static final String CAPITAL_ENTRY = "capitalEntry";

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private MBClientType client;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = MBOrderType.INVOICE)
    private Set<MBOrderType> orders = new HashSet<>();

    private long amount;
    private String invoiceNumber;
    private MBPaymentModeEnum paymentMode;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private MBCapitalEntryType capitalEntry;
}
