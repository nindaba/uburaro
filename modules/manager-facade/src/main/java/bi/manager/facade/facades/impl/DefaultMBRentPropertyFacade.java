package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBRentService;
import bi.manager.core.services.MBTypeService;
import bi.manager.core.types.MBRentPropertyType;
import bi.manager.facade.converters.rent.FullRentPropertyMapper;
import bi.manager.facade.converters.rent.RentPropertyMapper;
import bi.manager.facade.data.MBRentPropertyData;
import bi.manager.facade.facades.MBRentPropertyFacade;
import bi.uburaro.core.services.TypeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service(value = "mBRentPropertyFacade")
public class DefaultMBRentPropertyFacade implements MBRentPropertyFacade {

    protected final MBRentService rentService;
    protected final RentPropertyMapper mapper;
    protected final FullRentPropertyMapper fullMapper;
    protected final TypeService typeService;

    public DefaultMBRentPropertyFacade(MBRentService rentService, RentPropertyMapper mapper, FullRentPropertyMapper fullMapper, TypeService typeService) {
        this.rentService = rentService;
        this.mapper = mapper;
        this.fullMapper = fullMapper;
        this.typeService = typeService;
    }

    @Override
    public Collection<MBRentPropertyData> getRentsByFacilityCode(String facilityCode, boolean allFields) {
        Collection<MBRentPropertyType> rents = rentService.getRentsByFacilityCode(facilityCode);
        return allFields ? fullMapper.rentPropertiesToData(rents) : mapper.rentPropertiesToData(rents);
    }

    @Override
    public MBRentPropertyData getRentalPropertyByCode(String code, boolean allFields) {
        MBRentPropertyType itemByCode = typeService.findItemByCode(code, MBRentPropertyType.class);
        return allFields ? fullMapper.rentPropertyToData(itemByCode) : mapper.rentPropertyToData(itemByCode);
    }

    @Override
    public void deleteRentals(Set<String> codes) {
        rentService.deleteMBItem(codes);
    }

    @Override
    public void updateClient(MBRentPropertyData rental) {
        rentService.updateRent(
                fullMapper.rentPropertyToType(rental));
    }
}
