package bi.manager.core.types;

import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.enums.MBEntryEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = MBCapitalEntryType.ITEM_TYPE)
public class MBCapitalEntryType extends ItemType {
    public static final String ITEM_TYPE = "mBCapitalEntry";
    public static final String ENTRY_DATE = "entryDate";
    public static final String AMOUNT = "amount";
    public static final String ENTRY_TYPE = "entryType";

    private LocalDate entryDate;
    private Long amount;
    private MBEntryEnum entryType;
}
