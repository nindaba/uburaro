package bi.uburaro.core.types.groups;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = DiscountGroupType.ITEM_TYPE)
public class DiscountGroupType extends GroupType {
    public static final String ITEM_TYPE = "discountGroup";
    public static final String DISCOUNT_VALUE = "discountValue";

    private Double discountValue;

}
