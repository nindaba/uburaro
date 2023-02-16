package bi.manager.core.services.impl;

import bi.manager.core.services.MBClientService;
import bi.manager.core.services.MBTypeService;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBClientType;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service(value = "mBClientService")
public class DefaultMBClientService extends AbstractMBTypeService<MBClientType> implements MBClientService, MBTypeService<MBClientType> {


    public DefaultMBClientService(TypeService typeService) {
        super(typeService);
    }


    @Override
    public Collection<MBClientType> getClientsByFacilityCode(String facilityCode) {
        return typeService.findItemByCode(facilityCode, MBFacilityType.class).getClients().stream()
                .filter(ItemType::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public MBClientType getClientByCode(String code) {
        return typeService.findItemByCode(code, MBClientType.class);
    }

    @Override
    public void updateClient(final MBClientType client) {
        MBClientType itemByCode;
        try {
            itemByCode= typeService.findItemByCode(client.getCode(), MBClientType.class);
        } catch (NotFoundException e) {
            itemByCode = typeService.create(MBClientType.class);
            itemByCode.setCode(client.getCode());
        }
        itemByCode.setActive(true);
        populateClient(client,itemByCode);
        typeService.save(itemByCode);
    }

    private void populateClient(MBClientType source, MBClientType target) {
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }
        if (StringUtils.isNotEmpty(source.getAddress())) {
            target.setAddress(source.getAddress());
        }
    }

    @Override
    public MBClientType getItemByCodeWithTry(String code) {
        try {
            return getClientByCode(code);
        } catch (NotFoundException e) {
            return null;
        }
    }
}
