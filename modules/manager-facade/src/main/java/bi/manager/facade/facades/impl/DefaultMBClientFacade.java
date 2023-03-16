package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBClientService;
import bi.manager.core.types.client.MBClientType;
import bi.manager.facade.converters.client.ClientMapper;
import bi.manager.facade.converters.client.FullClientMapper;
import bi.manager.facade.data.MBClientData;
import bi.manager.facade.facades.MBClientFacade;
import bi.manager.facade.facades.MBInventoryOrderFacade;
import bi.manager.facade.facades.MBRentOrderFacade;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static bi.manager.core.ManagerCoreConstants.INVENTORY_ORDER_PREFIX;
import static bi.manager.core.ManagerCoreConstants.RENT_ORDER_PREFIX;

@Component(value = "mBClientFacade")
public class DefaultMBClientFacade implements MBClientFacade {

    protected final MBClientService service;
    protected final ClientMapper mapper;
    protected final FullClientMapper fullMapper;
    protected final Environment environment;
    protected final MBRentOrderFacade rentOrderFacade;
    protected final MBInventoryOrderFacade inventoryOrderFacade;

    public DefaultMBClientFacade(MBClientService service, ClientMapper mapper, FullClientMapper fullMapper, Environment environment, MBRentOrderFacade rentOrderFacade, MBInventoryOrderFacade inventoryOrderFacade) {
        this.service = service;
        this.mapper = mapper;
        this.fullMapper = fullMapper;
        this.environment = environment;
        this.rentOrderFacade = rentOrderFacade;
        this.inventoryOrderFacade = inventoryOrderFacade;
    }

    @Override
    public Collection<MBClientData> getClientsByFacilityCode(final String facilityCode,final boolean allFields) {

        Collection<MBClientType> clients = service.getClientsByFacilityCode(facilityCode);
        return allFields ? fullMapper.clientsToData(clients) : mapper.clientsToData(clients);
    }

    @Override
    public MBClientData getClientByCode(final String code, final boolean allFields) {
        MBClientType client = service.getClientByCode(code);
        client.getContracts().removeIf(contract -> !contract.isActive());
        return allFields ? fullMapper.clientToData(client) : mapper.clientToData(client);
    }

    @Override
    public void deleteClients(final Set<String> codes) {
        service.deleteMBItem(codes);
    }

    @Override
    public void updateClient(final MBClientData client) {
        service.updateClient(mapper.clientToType(client));
    }

    @Override
    public void deleteOrders(final Set<String> orderNumbers) {
        inventoryOrderFacade.deleteOrder(
                filterOrders(orderNumbers, INVENTORY_ORDER_PREFIX));

        rentOrderFacade.deleteOrder(
                filterOrders(orderNumbers, RENT_ORDER_PREFIX));
    }

    protected Set<String> filterOrders(final Set<String> orderNumbers,final String prefixKey) {
        final String prefix = environment.getProperty(prefixKey);
        return orderNumbers.stream()
                .filter(order -> order.startsWith(prefix))
                .collect(Collectors.toSet());
    }
}
