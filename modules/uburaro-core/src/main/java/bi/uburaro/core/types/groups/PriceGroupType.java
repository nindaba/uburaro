package bi.uburaro.core.types.groups;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = PriceGroupType.ITEM_TYPE)
public class PriceGroupType extends GroupType {
    public static final String ITEM_TYPE = "priceGroup";
    public static final String PRICE_VALUE = "priceValue";
    public static final String ROOMS = "rooms";

    private Double priceValue;

}
