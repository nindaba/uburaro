package bi.manager.core.services.impl;

import bi.manager.core.repositories.MBFacilityRepository;
import bi.manager.core.services.MBFacilityService;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBFacilityType;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "facilityService")
public class DefaultMBFacilityService implements MBFacilityService {
    private final TypeService typeService;

    public DefaultMBFacilityService(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public MBFacilityType getFacilityByCode(final String code) {
        return typeService.findItemByCode(code, MBFacilityType.class);
    }

    @Override
    public Collection<MBFacilityType> getAllFacilities() {
        return typeService.findAll(MBFacilityType.class).stream()
                .filter(MBFacilityType::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFacilities(final Set<String> facilityIds) {
        facilityIds.stream()
                .map(this::getFacilityByCode)
                .filter(Objects::nonNull)
                .peek(facility -> facility.setActive(false))
                .forEach(typeService::save);
    }

    @Override
    public MBFacilityType createFacility(final MBFacilityType facility) {
        try {

            final MBFacilityType facilityByCode = this.getFacilityByCode(facility.getCode());
            facilityByCode.setActive(true);
            typeService.save(facilityByCode);
            return facilityByCode;
        } catch (NotFoundException ex) {
            MBCapitalType mbCapitalType = typeService.create(MBCapitalType.class);
            mbCapitalType.setCurrentValue(0);
            facility.setCapital(mbCapitalType);
            typeService.save(facility);
            return facility;
        }
    }

    @Override
    public void updateFacility(final MBFacilityType facility) {
        final MBFacilityType facilityByCode = this.getFacilityByCode(facility.getCode());
        populateFacility(facility, facilityByCode);
        typeService.save(facilityByCode);
    }

    private void populateFacility(MBFacilityType source, MBFacilityType target) {
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }

        if (StringUtils.isNotEmpty(source.getAlias())) {
            target.setAlias(source.getAlias());
        }
        if (StringUtils.isNotEmpty(source.getAddress())) {
            target.setAddress(source.getAddress());
        }
    }
}
