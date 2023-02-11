package bi.manager.core.types;

import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.enums.MBEntryEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true ,exclude = {
        "capital"
})
@NoArgsConstructor
@Entity(name = MBCapitalEntryType.ITEM_TYPE)
public class MBCapitalEntryType extends ItemType {
    public static final String ITEM_TYPE = "mBCapitalEntry";
    public static final String AMOUNT = "amount";
    public static final String ENTRY_TYPE = "entryType";
    public static final String CAPITAL = "entryType";

    private long amount;
    private MBEntryEnum entryType;
    @ManyToOne(cascade = CascadeType.ALL)
    private MBCapitalType capital;
}
