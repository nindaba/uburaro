package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.LanguageType;
import bi.uburaro.facade.pupulators.Populator;
import bi.uburaro.facade.data.LanguageData;

public class LanguagePopulator implements Populator<LanguageType,LanguageData> {
    @Override
    public void populate(final LanguageType source, final LanguageData target) {
        target.setIsoCode(source.getIsoCode());
        target.setName(source.getName());
    }
}
