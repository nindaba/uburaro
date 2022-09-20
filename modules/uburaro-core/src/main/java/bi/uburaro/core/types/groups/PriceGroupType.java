package bi.uburaro.core.types.groups;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class PriceGroupType extends GroupType {
    public static final String ITEM_TYPE = "priceGroup";
    public static final String PRICE_VALUE = "priceValue";
    public static final String ROOMS = "rooms";

    private Double priceValue;

}
