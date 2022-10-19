package bi.uburaro.core.strategies;

import bi.uburaro.core.types.groups.DiscountGroupType;
import bi.uburaro.core.types.groups.PriceGroupType;
import bi.uburaro.core.types.groups.TaxGroupType;

import java.util.List;

public interface TaxResolverStrategy {
    TaxGroupType resolve(final PriceGroupType priceGroupType, final List<TaxGroupType> taxGroupTypes, final List<DiscountGroupType> discountGroupTypes);
}
