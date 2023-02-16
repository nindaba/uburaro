package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBClientService;
import bi.manager.core.types.client.MBClientType;
import bi.manager.facade.converters.client.ClientMapper;
import bi.manager.facade.converters.client.FullClientMapper;
import bi.manager.facade.data.MBClientData;
import bi.manager.facade.data.MBFacilityData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultMBClientFacadeTest {
    static final MBFacilityData FACILITY_DATA = new MBFacilityData();


    static final MBClientData CLIENT_DATA_1 = new MBClientData();
    static final MBClientData CLIENT_DATA_2 = new MBClientData();
    static final MBClientData CLIENT_DATA_3 = new MBClientData();

    static final MBClientType CLIENT_1 = new MBClientType();
    static final MBClientType CLIENT_2 = new MBClientType();
    static final MBClientType CLIENT_3 = new MBClientType();
    static Set<MBClientType> CLIENT_TYPES;

    @InjectMocks
    DefaultMBClientFacade facade;
    @Mock
    ClientMapper mapper;
    @Mock
    FullClientMapper fullMapper;
    @Mock
    MBClientService service;

    @BeforeEach
    void setUp() {
        CLIENT_DATA_1.setCode("cl1");
        CLIENT_DATA_1.setName("name1");
        CLIENT_DATA_1.setActive(true);

        CLIENT_DATA_2.setCode("cl2");
        CLIENT_DATA_2.setName("name2");
        CLIENT_DATA_2.setActive(false);

        CLIENT_DATA_3.setCode("cl3");
        CLIENT_DATA_3.setName("name3");
        CLIENT_DATA_3.setActive(true);

        CLIENT_1.setCode("cl1");
        CLIENT_1.setName("name1");
        CLIENT_1.setActive(true);

        CLIENT_2.setCode("cl2");
        CLIENT_2.setName("name2");
        CLIENT_2.setActive(false);

        CLIENT_3.setCode("cl3");
        CLIENT_3.setName("name3");
        CLIENT_3.setActive(true);

        CLIENT_TYPES = Set.of(CLIENT_1, CLIENT_2, CLIENT_3);
        FACILITY_DATA.setClients(Set.of(CLIENT_DATA_2, CLIENT_DATA_3, CLIENT_DATA_1));
    }

    @Test
    void getClientsByFacilityCode() {
        when(service.getClientsByFacilityCode(FACILITY_DATA.getCode())).thenReturn(CLIENT_TYPES);

        facade.getClientsByFacilityCode(FACILITY_DATA.getCode(), false);
        verify(mapper).clientsToData(CLIENT_TYPES);

        facade.getClientsByFacilityCode(FACILITY_DATA.getCode(), true);
        verify(fullMapper).clientsToData(CLIENT_TYPES);
    }

    @Test
    void getClientByCode() {
        when(service.getClientByCode(CLIENT_3.getCode())).thenReturn(CLIENT_3);

        facade.getClientByCode(CLIENT_3.getCode(), false);
        verify(mapper).clientToData(CLIENT_3);

        facade.getClientByCode(CLIENT_3.getCode(), true);
        verify(fullMapper).clientToData(CLIENT_3);
    }

    @Test
    void deleteClients() {
        Set<String> codes = Set.of(CLIENT_1.getCode(), CLIENT_2.getCode(), CLIENT_3.getCode());
        doNothing().when(service).deleteMBItem(codes);
        facade.deleteClients(codes);
        verify(service).deleteMBItem(codes);
    }

    @Test
    void updateClient() {
        doNothing().when(service).updateClient(CLIENT_3);
        when(mapper.clientToType(CLIENT_DATA_3)).thenReturn(CLIENT_3);
        facade.updateClient(CLIENT_DATA_3);
        verify(service).updateClient(CLIENT_3);
    }
}