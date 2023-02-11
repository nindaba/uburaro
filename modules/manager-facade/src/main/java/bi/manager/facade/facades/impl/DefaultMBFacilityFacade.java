package bi.manager.facade.facades.impl;

import bi.manager.core.types.MBCapitalType;
import bi.manager.facade.facades.MBFacilityFacade;
import bi.manager.core.services.MBFacilityService;
import bi.manager.core.types.MBFacilityType;
import bi.manager.facade.converters.facility.FacilityMapper;
import bi.manager.facade.converters.facility.FullFacilityMapper;
import bi.manager.facade.data.MBFacilityData;
import bi.uburaro.core.services.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Set;

@Service(value = "facilityFacade")
public class DefaultMBFacilityFacade implements MBFacilityFacade {

    private final MBFacilityService facilityService;
    private final FacilityMapper facilityMapper;
    private final FullFacilityMapper fullFacilityMapper;
    private final TypeService typeService;

    public DefaultMBFacilityFacade(MBFacilityService facilityService, FacilityMapper facilityMapper, FullFacilityMapper fullFacilityMapper, TypeService typeService) {
        this.facilityService = facilityService;
        this.facilityMapper = facilityMapper;
        this.fullFacilityMapper = fullFacilityMapper;
        this.typeService = typeService;
    }


    @Override
    public Collection<MBFacilityData> getAllFacilities(final boolean allFields) {
        final Collection<MBFacilityType> allFacilities = facilityService.getAllFacilities();
        return allFields ? fullFacilityMapper.facilitiesToData(allFacilities) : facilityMapper.facilitiesToData(allFacilities);
    }

    @Override
    public MBFacilityData getFacilityByCode(final String code, final boolean allFields) {
        final MBFacilityType facilityByCode = facilityService.getFacilityByCode(code);
        return allFields ? fullFacilityMapper.facilityToData(facilityByCode) : facilityMapper.facilityToData(facilityByCode);
    }

    @Override
    public void deleteFacilities(final Set<String> codes) {
        facilityService.deleteFacilities(codes);
    }

    @Override
    public void updateFacility(final String code, final MBFacilityData facility) {
        final MBFacilityType facilityByCode = facilityService.getFacilityByCode(code);

        if (facilityByCode != null) {
            populateType(facility, facilityByCode);
        }

        facilityService.updateFacility(facilityByCode);
    }

    @Override
    public void createFacility(final MBFacilityData facilityData) {
        Assert.notNull(facilityData, "Facility must not be null");
        MBFacilityType facilityType = typeService.create(MBFacilityType.class);

        if(facilityData.getCapital() == null){
            MBCapitalType capital = typeService.create(MBCapitalType.class);
            facilityType.setCapital(capital);
        }
        populateType(facilityData, facilityType);

        facilityService.createFacility(facilityType);
    }

    private void populateType(MBFacilityData facilityData, MBFacilityType facilityType) {
        facilityType.setActive(true);
        if(StringUtils.isBlank(facilityType.getCode())){
            facilityType.setCode(facilityData.getCode());
        }
        if (StringUtils.isNotBlank(facilityData.getName())) {
            facilityType.setName(facilityData.getName());
        }
        if (StringUtils.isNotBlank(facilityData.getAlias())) {
            facilityType.setAlias(facilityData.getAlias());
        }
        if (StringUtils.isNotBlank(facilityData.getAddress())) {
            facilityType.setAddress(facilityData.getAddress());
        }
    }
}
