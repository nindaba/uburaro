package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.groups.TaxGroupType;
import bi.uburaro.facade.pupulators.Populator;
import bi.uburaro.facade.data.groups.TaxGroupData;

public class TaxGroupPopulator implements Populator<TaxGroupType, TaxGroupData> {

    @Override
    public void populate(TaxGroupType source, TaxGroupData target) {
        target.setTaxPriority(source.getTaxPriority());
        target.setTaxValue(source.getTaxValue());
    }
}
