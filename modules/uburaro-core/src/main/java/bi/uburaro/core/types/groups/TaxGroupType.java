package bi.uburaro.core.types.groups;

import bi.uburaro.core.types.CompanyType;
import bi.uburaro.core.types.RoomType;
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
@EqualsAndHashCode(callSuper = true, exclude = {
        "taxedCompanies",
        "rooms"
})
@NoArgsConstructor
@Entity(name = TaxGroupType.ITEM_TYPE)
public class TaxGroupType extends GroupType {
    public static final String ITEM_TYPE = "taxGroup";
    public static final String TAX_VALUE = "taxValue";
    public static final String TAX_PRIORITY = "taxPriority";
    public static final String TAXED_COMPANIES = "taxedCompanies";
    public static final String ROOMS = "rooms";

    private Double taxValue;
    private Integer taxPriority;
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
    private Set<CompanyType> taxedCompanies = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "rooms2room_groups",
            joinColumns = {
                    @JoinColumn(name = "rooms_date_created"),
                    @JoinColumn(name = "rooms_item_type"),
                    @JoinColumn(name = "rooms_t_key")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "tax_groups_date_created"),
                    @JoinColumn(name = "tax_groups_item_type"),
                    @JoinColumn(name = "tax_groups_t_key")
            })
    private Set<RoomType> rooms = new HashSet<>();
}
