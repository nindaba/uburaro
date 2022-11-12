package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.DiscountGroupType;
import bi.uburaro.core.types.groups.RoomGroupType;
import bi.uburaro.core.types.groups.TaxGroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = CompanyType.ITEM_TYPE)
public class CompanyType extends ItemType{
    public static final String ITEM_TYPE = "company";
    public static final String COMPANY_NAME = "companyName";
    public static final String PREFERRED_ROOMS_GROUPS = "preferredRoomsGroups";
    public static final String DISCOUNT_GROUPS = "discountGroups";
    public static final String TAX_GROUPS = "taxGroups";
    public static final String CUSTOMERS = "customers";

    private String companyName;
    @ManyToMany
    private Set<RoomGroupType> preferredRoomsGroups;
    @ManyToMany
    private Set<DiscountGroupType> discountGroups;
    @ManyToMany
    private Set<TaxGroupType> taxGroups;
    @ManyToMany
    private Set<CustomerType> customers;
}
