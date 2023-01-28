package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.BranchGroupType;
import bi.uburaro.core.types.groups.TaxGroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "rooms",
        "languages",
        "addresses",
        "taxGroups"
})
@NoArgsConstructor
@Entity(name = HotelType.ITEM_TYPE)
public class HotelType extends ItemType {
    public static final String ITEM_TYPE = "hotel";
    public static final String NAME = "name";
    public static final String ALIAS = "alias";
    public static final String ROOMS = "rooms";
    public static final String LANGUAGES = "languages";
    public static final String DEFAULT_LANGUAGE = "defaultLanguage";
    public static final String ADDRESS = "addresses";
    public static final String TAX_GROUPS = "taxGroups";
    public static final String DEFAULT_TAX_GROUP = "defaultTaxGroup";
    public static final String BRANCH_GROUP = "branchGroup";


    private String name;
    private String alias;
    @OneToOne
    private LanguageType defaultLanguage;
    @OneToOne
    private TaxGroupType defaultTaxGroup;
    @ManyToOne
    private BranchGroupType branchGroup;
    @OneToMany
    private Set<RoomType> rooms;
    @OneToMany
    private Set<LanguageType> languages;
    @OneToMany
    private Set<AddressType> addresses;
    @OneToMany
    private Set<TaxGroupType> taxGroups;
}
