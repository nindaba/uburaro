package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBClientService;
import bi.manager.core.types.client.MBClientType;
import bi.manager.facade.converters.client.ClientMapper;
import bi.manager.facade.converters.client.FullClientMapper;
import bi.manager.facade.data.MBClientData;
import bi.manager.facade.facades.MBClientFacade;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component(value = "mBClientFacade")
public class DefaultMBClientFacade implements MBClientFacade {

    protected final MBClientService service;
    protected final ClientMapper mapper;
    protected final FullClientMapper fullMapper;

    public DefaultMBClientFacade(MBClientService service, ClientMapper mapper, FullClientMapper fullMapper) {
        this.service = service;
        this.mapper = mapper;
        this.fullMapper = fullMapper;
    }

    @Override
    public Collection<MBClientData> getClientsByFacilityCode(final String facilityCode,final boolean allFields) {

        Collection<MBClientType> clients = service.getClientsByFacilityCode(facilityCode);
        return allFields ? fullMapper.clientsToData(clients) : mapper.clientsToData(clients);
    }

    @Override
    public MBClientData getClientByCode(final String code, final boolean allFields) {
        MBClientType client = service.getClientByCode(code);
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
}
