package bi.uburaro.core.types.groups;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class DiscountGroupType extends GroupType {
    public static final String ITEM_TYPE = "priceGroup";
    public static final String DISCOUNT_VALUE = "discountValue";

    private Double discountValue;

}
