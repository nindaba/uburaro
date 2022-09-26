package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.DiscountGroupType;
import bi.uburaro.core.types.groups.GroupType;
import bi.uburaro.core.types.groups.TaxGroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class CompanyType extends ItemType{
    public static final String ITEM_TYPE = "company";
    public static final String COMPANY_NAME = "companyName";
    public static final String GROUPS = "groups";
    public static final String DISCOUNT_GROUPS = "discountGroups";
    public static final String TAX_GROUPS = "taxGroups";

    private String companyName;
    private Set<GroupType> groups;
    private Set<DiscountGroupType> discountGroups;
    private Set<TaxGroupType> taxGroups;
}
