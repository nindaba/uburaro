package bi.manager.core.types.client;

import bi.manager.core.types.MBFacilityType;
import bi.uburaro.core.types.ItemType;
import bi.manager.core.types.MBInventoryOrderType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "contracts",
        "invoices",
        "facility",
        "orders"
})
@ToString(callSuper = true, exclude = {
        "orders",
        "invoices",
        "facility",
        "contracts"
})
@NoArgsConstructor
@Entity(name = MBClientType.ITEM_TYPE)
public class MBClientType extends ItemType {
    public static final String ITEM_TYPE = "mBClient";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String INVOICES = "invoices";
    public static final String ORDERS = "orders";
    public static final String TOTAL_DEBT = "totalDebt";
    public static final String FACILITY = "facility";
    public static final String CONTRACTS = "contracts";


    private String name;
    private String address;
    private long totalDebt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = MBInventoryOrderType.CLIENT)
    private Set<MBOrderType> orders = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL , mappedBy = MBInvoiceType.CLIENT)
    private Set<MBInvoiceType> invoices = new HashSet<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private MBFacilityType facility;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = MBRentContractType.CLIENT)
    private Set<MBRentContractType> contracts = new HashSet<>();
}
