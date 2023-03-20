package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBCapitalService;
import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.manager.facade.converters.facility.FullCapitalMapper;
import bi.manager.facade.data.MBCapitalData;
import bi.manager.facade.data.MBCapitalEntryData;
import bi.manager.facade.facades.MBCapitalFacade;
import bi.uburaro.core.services.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Date;

@Service(value = "mbCapitalFacade")
public class DefaultMBCapitalFacade implements MBCapitalFacade {
    protected final MBCapitalService capitalService;
    protected final FullCapitalMapper capitalMapper;
    protected final TypeService typeService;

    public DefaultMBCapitalFacade(final MBCapitalService capitalService, final FullCapitalMapper capitalMapper, TypeService typeService) {
        this.capitalService = capitalService;
        this.capitalMapper = capitalMapper;
        this.typeService = typeService;
    }

    @Override
    public void addCapital(final String facilityCode, final long value, final String type,final String description) {
        Assert.hasText(facilityCode,"Facility code can not be empty");
        Assert.hasText(type,"Type code can not be empty");

        final MBEntryEnum entryEnum = MBEntryEnum.valueOf(type.toUpperCase());
        final MBCapitalEntryType entry = typeService.create(MBCapitalEntryType.class);

        entry.setDescription(description);
        entry.setAmount(value);
        entry.setEntryType(entryEnum);
        capitalService.addCapital(entry,facilityCode);
    }

    @Override
    public MBCapitalData getCapitalByFacility(final String facilityCode) {
        return capitalMapper.capitalToData(capitalService.getCapitalByFacility(facilityCode));
    }

    @Override
    public Collection<MBCapitalEntryData> getCapitalEntries(String facilityCode, final Date from, final Date to) {
        return capitalMapper.capitalEntriesToData(
                capitalService.getCapitalEntries(facilityCode, from, to));
    }
}
