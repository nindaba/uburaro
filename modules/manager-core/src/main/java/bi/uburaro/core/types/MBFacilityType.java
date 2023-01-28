package bi.uburaro.core.types;

import bi.uburaro.core.types.client.MBClientType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "MBClients",
        "MBCategories",
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

    private String name;
    private String alias;
    private String address;

    @OneToMany
    private Set<MBCategoryType> categories = new HashSet<>();
    @OneToMany
    private Set<MBClientType> clients = new HashSet<>();
    @OneToOne
    private MBCapitalType capital;
}
