package bi.uburaro.core.types.groups;

import bi.uburaro.core.types.CompanyType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = true, exclude = {"discountedCompanies"})
@NoArgsConstructor
@Entity(name = DiscountGroupType.ITEM_TYPE)
public class DiscountGroupType extends GroupType {
    public static final String ITEM_TYPE = "discountGroup";
    public static final String DISCOUNTED_COMPANIES = "discountedCompanies";
    public static final String DISCOUNT_VALUE = "discountValue";

    private Double discountValue;

    @ManyToMany
    @JoinTable(name = "company2discount_group",
            joinColumns = {
                    @JoinColumn(name = "discounted_companies_date_created"),
                    @JoinColumn(name = "discounted_companies_t_key"),
                    @JoinColumn(name = "discounted_companies_item_type"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "discount_groups_date_created"),
                    @JoinColumn(name = "discount_groups_t_key"),
                    @JoinColumn(name = "discount_groups_item_type"),
            })
    private Set<CompanyType> discountedCompanies = new HashSet<>();
}
