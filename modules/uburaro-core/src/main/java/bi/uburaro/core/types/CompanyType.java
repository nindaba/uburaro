package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.DiscountGroupType;
import bi.uburaro.core.types.groups.RoomGroupType;
import bi.uburaro.core.types.groups.TaxGroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = CompanyType.ITEM_TYPE)
public class CompanyType extends ItemType {
    public static final String ITEM_TYPE = "company";
    public static final String COMPANY_NAME = "companyName";
    public static final String PREFERRED_ROOMS_GROUPS = "preferredRoomsGroups";
    public static final String DISCOUNT_GROUPS = "discountGroups";
    public static final String TAX_GROUPS = "taxGroups";
    public static final String CUSTOMERS = "customers";

    private String companyName;
    @ManyToMany
    @JoinTable(name = "preferred_companies2preferred_room_groups",
            joinColumns = {
                    @JoinColumn(name = "preferred_companies_date_created"),
                    @JoinColumn(name = "preferred_companies_t_key"),
                    @JoinColumn(name = "preferred_companies_item_type"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "preferred_room_groups_date_created"),
                    @JoinColumn(name = "preferred_room_groups_t_key"),
                    @JoinColumn(name = "preferred_room_groups_item_type"),
            })


    private Set<RoomGroupType> preferredRoomsGroups = new HashSet<>();
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


    private Set<DiscountGroupType> discountGroups = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "taxed_companies2tax_groups",
            joinColumns = {
                    @JoinColumn(name = "tax_groups_date_created"),
                    @JoinColumn(name = "tax_groups_item_type"),
                    @JoinColumn(name = "tax_groups_t_key"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "taxed_companies_date_created"),
                    @JoinColumn(name = "taxed_companies_item_type"),
                    @JoinColumn(name = "taxed_companies_t_key"),
            })


    private Set<TaxGroupType> taxGroups = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "customers2companies",
            joinColumns = {
                    @JoinColumn(name = "companies_date_created"),
                    @JoinColumn(name = "companies_item_type"),
                    @JoinColumn(name = "companies_t_key"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "customers_date_created"),
                    @JoinColumn(name = "customers_item_type"),
                    @JoinColumn(name = "customers_t_key"),
            })
    private Set<CustomerType> customers = new HashSet<>();
}
