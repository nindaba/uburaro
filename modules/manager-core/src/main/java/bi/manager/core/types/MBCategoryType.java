package bi.manager.core.types;

import bi.uburaro.core.types.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "inventories",
})
@NoArgsConstructor
@Entity(name = MBCategoryType.ITEM_TYPE)
public class MBCategoryType extends ItemType {
    public static final String ITEM_TYPE = "mBCategory";
    public static final String NAME = "name";
    public static final String INVENTORIES = "inventories";


    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<MBInventoryType> inventories = new HashSet<>();

}
