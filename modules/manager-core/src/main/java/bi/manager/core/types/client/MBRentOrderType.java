package bi.manager.core.types.client;

import bi.manager.core.types.MBRentPropertyType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"rentProperty"})
@ToString(callSuper = true, exclude = {"rentProperty"})
@NoArgsConstructor
@Entity(name = MBRentOrderType.ITEM_TYPE)
public class MBRentOrderType extends MBOrderType {
    public static final String ITEM_TYPE = "mBRentOrder";
    public static final String RENT_PROPERTY = "rentProperty";
    public static final String CLIENT = "client";

    @ManyToOne(cascade = CascadeType.ALL)
    private MBRentPropertyType rentProperty;
}
