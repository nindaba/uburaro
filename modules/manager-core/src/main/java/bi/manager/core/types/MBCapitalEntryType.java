package bi.manager.core.types;

import bi.manager.core.types.client.MBInvoiceType;
import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.enums.MBEntryEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true ,exclude = {
        "capital"
})
@ToString(callSuper = true, exclude = {
        "capital"
})
@NoArgsConstructor
@Entity(name = MBCapitalEntryType.ITEM_TYPE)
public class MBCapitalEntryType extends ItemType {
    public static final String ITEM_TYPE = "mBCapitalEntry";
    public static final String AMOUNT = "amount";
    public static final String ENTRY_TYPE = "entryType";
    public static final String CAPITAL = "capital";
    public static final String DESCRIPTION = "description";

    private long amount;
    private MBEntryEnum entryType;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private MBCapitalType capital;

    @OneToOne(cascade = CascadeType.ALL)
    private MBInvoiceType invoice;
}
