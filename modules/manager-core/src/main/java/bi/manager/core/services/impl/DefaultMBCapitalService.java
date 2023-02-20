package bi.manager.core.services.impl;

import bi.manager.core.services.MBCapitalService;
import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.uburaro.core.services.TypeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Service(value = "mbCapitalService")
public class DefaultMBCapitalService implements MBCapitalService {
    protected final TypeService typeService;

    public DefaultMBCapitalService(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public void addCapital(final String facilityCode, final long value, final MBEntryEnum type) {
        MBFacilityType facility = typeService.findItemByCode(facilityCode, MBFacilityType.class);
        addCapital(value, type, facility);
    }

    public void addCapital(long value, MBEntryEnum type, MBFacilityType facility) {
        MBCapitalType capital = facility.getCapital();

        if (capital == null) {
            capital = typeService.create(MBCapitalType.class);
            facility.setCapital(capital);
            typeService.save(facility);
        }

        long currentValue = capital.getCurrentValue();
        currentValue += type == MBEntryEnum.EXPENSE ? -value : value;

        capital.setCurrentValue(currentValue);
        MBCapitalEntryType entry = typeService.create(MBCapitalEntryType.class);
        entry.setEntryType(type);
        entry.setAmount(value);
        entry.setCapital(capital);
        typeService.save(entry);
    }

    @Override
    public MBCapitalType getCapitalByFacility(String facilityCode) {
        MBFacilityType facility = typeService.findItemByCode(facilityCode, MBFacilityType.class);
        return facility.getCapital();
    }

    @Override
    public Collection<MBCapitalEntryType> getCapitalEntries(String facilityCode, Date from, Date to) {
        MBFacilityType facility = typeService.findItemByCode(facilityCode, MBFacilityType.class);

        if (facility != null) {
            return facility.getCapital().getEntries().stream()
                    .filter(entry -> entry.getDateModified().compareTo(from) + to.compareTo(entry.getDateModified()) >= 0)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
