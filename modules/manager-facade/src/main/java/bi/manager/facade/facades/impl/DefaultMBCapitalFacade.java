package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBCapitalService;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.manager.facade.converters.facility.FullCapitalMapper;
import bi.manager.facade.data.MBCapitalData;
import bi.manager.facade.data.MBCapitalEntryData;
import bi.manager.facade.facades.MBCapitalFacade;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Date;

@Service(value = "mbCapitalFacade")
public class DefaultMBCapitalFacade implements MBCapitalFacade {
    protected final MBCapitalService capitalService;
    protected final FullCapitalMapper capitalMapper;

    public DefaultMBCapitalFacade(final MBCapitalService capitalService, final FullCapitalMapper capitalMapper) {
        this.capitalService = capitalService;
        this.capitalMapper = capitalMapper;
    }

    @Override
    public void addCapital(final String facilityCode, final long value, final String type) {
        Assert.hasText(facilityCode,"Facility code can not be empty");
        Assert.hasText(type,"Type code can not be empty");

        MBEntryEnum entryEnum = MBEntryEnum.valueOf(type.toUpperCase());
        capitalService.addCapital(facilityCode, value, entryEnum);
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
