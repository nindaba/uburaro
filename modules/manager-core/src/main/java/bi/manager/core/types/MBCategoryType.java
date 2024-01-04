package bi.manager.core.types;

import bi.uburaro.core.types.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "inventories",
        "facility"
})
@ToString(callSuper = true, exclude = {
        "inventories",
        "facility"
})
@NoArgsConstructor
@Entity(name = MBCategoryType.ITEM_TYPE)
public class MBCategoryType extends ItemType {
    public static final String ITEM_TYPE = "mBCategory";
    public static final String NAME = "name";
    public static final String INVENTORIES = "inventories";
    public static final String FACILITY = "facility";


    private String name;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = MBInventoryType.CATEGORY)
    private Set<MBInventoryType> inventories = new HashSet<>();
    @ManyToOne
    private MBFacilityType facility;

}
