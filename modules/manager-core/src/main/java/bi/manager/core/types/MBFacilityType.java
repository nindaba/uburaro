package bi.manager.core.types;

import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBOrderType;
import bi.uburaro.core.types.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "categories",
        "categories",
        "capital"
})
@ToString(callSuper = true, exclude = {
        "categories",
        "categories",
        "capital"
})
@NoArgsConstructor
@Entity(name = MBFacilityType.ITEM_TYPE)
public class MBFacilityType extends ItemType {
    public static final String ITEM_TYPE = "mBFacility";
    public static final String NAME = "name";
    public static final String ALIAS = "alias";
    public static final String ADDRESS = "address";
    public static final String CATEGORIES = "categories";
    public static final String CLIENTS = "clients";
    public static final String CAPITAL = "capital";

    private String name;
    private String alias;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = MBCategoryType.FACILITY)
    private Set<MBCategoryType> categories = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = MBClientType.FACILITY)
    private Set<MBClientType> clients = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    private MBCapitalType capital;
}
