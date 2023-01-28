package bi.uburaro.core.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "entries"
})
@NoArgsConstructor
@Entity(name = MBCapitalType.ITEM_TYPE)
public class MBCapitalType extends ItemType{
    public static final String ITEM_TYPE = "mBCapital";
    public static final String ENTRIES = "entries";

    @OneToMany
    private Set<MBCapitalEntryType> entries = new HashSet<>();
}
