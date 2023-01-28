package bi.uburaro.core.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "rooms",
        "languages",
        "addresses",
        "taxGroups"
})
@NoArgsConstructor
@Entity(name = MBChargeType.ITEM_TYPE)
public class MBChargeType extends ItemType{
    public static final String ITEM_TYPE = "mBCharge";
    public static final String CHARGE_ENTRY = "chargeEntry";

}
