package bi.manager.core.types.client;

import bi.manager.core.types.MBRentPropertyType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"rentProperty"})
@ToString(callSuper = true, exclude = {"rentProperty"})
@NoArgsConstructor
@Entity(name = MBRentOrderType.ITEM_TYPE)
public class MBRentOrderType extends MBOrderType {
    public static final String ITEM_TYPE = "mBRentOrder";
    public static final String RENT_PROPERTY = "rentProperty";
    public static final String UNIT_CHARGED = "unitCharged";
    public static final String TOTAL_UNIT_CHARGED = "totalUnitCharged";
    public static final String FROM = "from";
    public static final String TO = "to";

    @ManyToOne(cascade = CascadeType.ALL)
    private MBRentPropertyType rentProperty;

    private LocalDate from;
    private LocalDate to;
    private int unitCharged;
    private int totalUnitCharged;
    private int units;
}
