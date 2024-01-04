package bi.manager.core.services.impl;

import bi.manager.core.services.MBTypeService;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBClientType;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Set;

public abstract class AbstractMBTypeService<ITEM extends ItemType> implements MBTypeService<ITEM> {
    protected final TypeService typeService;

    protected AbstractMBTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public void deleteMBItem(Set<String> codes) {
        codes.stream()
                .map(this::getItemByCodeWithTry)
                .filter(Objects::nonNull)
                .peek(itemType -> itemType.setActive(false))
                .forEach(typeService::save);
    }
    protected MBFacilityType validateAndGetFacility(MBFacilityType facility) {
        if (facility == null || StringUtils.isEmpty(facility.getCode())) {
            throw new NotFoundException("No Facility found on the provided");
        }

        MBFacilityType itemByCode = typeService.findItemByCode(facility.getCode(), MBFacilityType.class);
        return itemByCode;
    }
}
