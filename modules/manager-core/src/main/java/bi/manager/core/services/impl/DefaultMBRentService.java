package bi.manager.core.services.impl;

import bi.manager.core.services.MBRentService;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.stream.Collectors;

@Service(value = "mBRentService")
public class DefaultMBRentService extends AbstractMBTypeService<MBRentPropertyType> implements MBRentService {

    protected final TypeService typeService;

    public DefaultMBRentService(TypeService typeService) {
        super(typeService);
        this.typeService = typeService;
    }

    @Override
    public Collection<MBRentPropertyType> getRentsByFacilityCode(final String facilityCode) {
        return typeService.findItemByCode(facilityCode, MBFacilityType.class)
                .getRents().stream()
                .filter(ItemType::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public void updateRent(final MBRentPropertyType rent) {
        final MBFacilityType facility = validateAndGetFacility(rent.getFacility());
        final MBRentPropertyType newRent = getOrCreateRent(rent);

        newRent.setFacility(facility);
        populateRent(rent, newRent);
        newRent.setActive(true);

        populateClient(rent, newRent);

        typeService.save(newRent);
    }

    private void populateRent(final MBRentPropertyType source, final MBRentPropertyType target) {
        target.setCode(source.getCode());

        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }

        if (StringUtils.isNotEmpty(source.getAddress())) {
            target.setAddress(source.getAddress());
        }

        if (source.getUnit() > 0) {
            target.setUnit(source.getUnit());
        }

        if (source.getCost() > 0) {
            target.setCost(source.getCost());
        }
    }

    private void populateClient(final MBRentPropertyType source,final MBRentPropertyType target) {
        final MBClientType currentClient = source.getCurrentClient();

        if (currentClient != null && StringUtils.isNotEmpty(currentClient.getCode())) {
            final MBClientType client = typeService.findItemByCode(currentClient.getCode(), MBClientType.class);
            target.setCurrentClient(client);
        }
        if (currentClient != null){
            target.setCurrentClient(null);
        }
    }

    private MBRentPropertyType getOrCreateRent(final MBRentPropertyType rent) {
        Assert.notNull(rent, "Rent must not be null");
        Assert.notNull(rent.getCost(), "Rent code must not be null");

        try {
            return typeService.findItemByCode(rent.getCode(), MBRentPropertyType.class);
        } catch (Exception e) {
            return typeService.create(MBRentPropertyType.class);
        }
    }

    @Override
    public MBRentPropertyType getItemByCodeWithTry(String code) {
        try {
            return typeService.findItemByCode(code, MBRentPropertyType.class);
        } catch (NotFoundException e) {
            return null;
        }
    }
}
