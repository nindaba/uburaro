package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.groups.PriceGroupType;
import bi.uburaro.facade.pupulators.Populator;
import bi.uburaro.facade.data.groups.PriceGroupData;

public class PriceGroupPopulator implements Populator<PriceGroupType, PriceGroupData> {
    @Override
    public void populate(PriceGroupType source, PriceGroupData target) {
        target.setPriceValue(source.getPriceValue());
    }
}
