package bi.manager.core.types.client;

import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.MBRentPropertyType;
import bi.uburaro.core.types.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static bi.uburaro.core.UburaroCoreConstants.TABLE_PREFIX;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"rentProperty","client"})
@ToString(callSuper = true, exclude = {"rentProperty","client"})
@NoArgsConstructor
@Entity(name = MBRentContractType.ITEM_TYPE)
public class MBRentContractType extends ItemType {
    public static final String ITEM_TYPE = "mBRentContract";
    public static final String RENT_PROPERTY = "rentProperty";
    public static final String CLIENT = "client";
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String ORDERS = "orders";
    public static final String COST_PER_UNIT = "costPerUnit";
    public static final String UNIT = "unit";
    public static final String CONTRACT_NAME = "contractFileName";


    @Column(name = TABLE_PREFIX+FROM)
    private LocalDate from;
    @Column(name = TABLE_PREFIX+TO)
    private LocalDate to;
    private long costPerUnit;
    private int unit;
    private String contractFileName;

    @ManyToOne(cascade = CascadeType.ALL)
    private MBRentPropertyType rentProperty;
    @ManyToOne(cascade = CascadeType.ALL)
    private MBClientType client;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = MBRentOrderType.CONTRACT)
    private Set<MBRentOrderType> orders = new HashSet<>();
}
