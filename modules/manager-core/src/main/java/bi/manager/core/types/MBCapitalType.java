package bi.manager.core.types;

import bi.uburaro.core.types.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "entries",
        "facility"
})
@NoArgsConstructor
@Entity(name = MBCapitalType.ITEM_TYPE)
public class MBCapitalType extends ItemType {
    public static final String ITEM_TYPE = "mBCapital";
    public static final String ENTRIES = "entries";
    public static final String CURRENT_VALUE = "currentValue";
    public static final String FACILITY = "facility";

    private long currentValue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "capital")
    private Set<MBCapitalEntryType> entries = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "capital")
    private MBFacilityType facility;
}
