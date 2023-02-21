package bi.manager.core.services.impl;

import bi.manager.core.repositories.MBOrderRepository;
import bi.manager.core.services.MBClientService;
import bi.manager.core.services.MBFacilityService;
import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBOrderType;
import bi.manager.core.types.enums.MBInventoryEntryEnum;
import bi.uburaro.core.repositories.GeneratedKeyRepository;
import bi.uburaro.core.services.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

import java.util.Set;

public class AbstractOrderService {
    protected final MBFacilityService facilityService;
    protected final MBClientService clientService;
    protected final TypeService typeService;
    protected final GeneratedKeyRepository generatedKeyRepository;
    protected final Environment environment;
    protected final MBOrderRepository orderRepository;

    protected AbstractOrderService(MBFacilityService facilityService, MBClientService clientService, TypeService typeService, GeneratedKeyRepository generatedKeyRepository, Environment environment, MBOrderRepository orderRepository) {
        this.facilityService = facilityService;
        this.clientService = clientService;
        this.typeService = typeService;
        this.generatedKeyRepository = generatedKeyRepository;
        this.environment = environment;
        this.orderRepository = orderRepository;
    }

    protected void chargeClient(final MBOrderType orderType) {
        MBClientType client = orderType.getClient();
        long debt = client.getTotalDebt() + orderType.getQuantity() * orderType.getCost();
        client.setTotalDebt(debt);
    }

    protected void populateClient(final MBOrderType source, final MBOrderType target) {
        MBClientType client = source.getClient();
        if (client != null && StringUtils.isNotEmpty(client.getCode())) {
            MBClientType clientByCode = clientService.getClientByCode(client.getCode());
            target.setClient(clientByCode);
        }
    }

    public void revertClient(MBOrderType order) {
        if (order instanceof MBInventoryOrderType
                && ((MBInventoryOrderType) order).getOrderEntry() == MBInventoryEntryEnum.SOLD) {

            MBClientType client = order.getClient();
            long debt = client.getTotalDebt() - order.getQuantity() * order.getCost();
            client.setTotalDebt(debt);
            typeService.save(client);
        }
    }
}
