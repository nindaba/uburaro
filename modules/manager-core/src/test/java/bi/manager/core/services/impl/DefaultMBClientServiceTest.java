package bi.manager.core.services.impl;

import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBClientType;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.TypeService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultMBClientServiceTest {

    static final MBFacilityType FACILITY_TYPE = new MBFacilityType();
    static final MBClientType CLIENT_1 = new MBClientType();
    static final MBClientType CLIENT_2 = new MBClientType();
    static final MBClientType CLIENT_3 = new MBClientType();

    @InjectMocks
    DefaultMBClientService service;
    @Mock
    TypeService typeService;


    @BeforeEach
    void setUp() {

        CLIENT_1.setCode("cl1");
        CLIENT_1.setName("name1");
        CLIENT_1.setActive(true);
        CLIENT_1.setFacility(FACILITY_TYPE);

        CLIENT_2.setCode("cl2");
        CLIENT_2.setName("name2");
        CLIENT_2.setActive(false);

        CLIENT_3.setCode("cl3");
        CLIENT_3.setName("name3");
        CLIENT_3.setActive(true);
        CLIENT_3.setFacility(FACILITY_TYPE);

        FACILITY_TYPE.setCode("facility");
        FACILITY_TYPE.setClients(Set.of(CLIENT_1,CLIENT_2,CLIENT_3));
    }

    @Test
    void getClientsByFacilityCode() {
        when(typeService.findItemByCode(FACILITY_TYPE.getCode(), MBFacilityType.class)).thenReturn(FACILITY_TYPE);
        Collection<MBClientType> actual = service.getClientsByFacilityCode(FACILITY_TYPE.getCode());
        assertEquals(2, actual.size());
    }

    @Test
    void getClientByCode() {
        when(typeService.findItemByCode(CLIENT_3.getCode(), MBClientType.class)).thenReturn(CLIENT_3);
        MBClientType actual = service.getClientByCode(CLIENT_3.getCode());
        assertEquals(CLIENT_3,actual);
    }

    @Test
    void updateClient() {
        MBClientType CLIENT_SPY = spy(CLIENT_3);
        when(typeService.findItemByCode(FACILITY_TYPE.getCode(), MBFacilityType.class)).thenReturn(FACILITY_TYPE);
        when(typeService.findItemByCode(CLIENT_1.getCode(), MBClientType.class)).thenThrow(NotFoundException.class);
        when(typeService.findItemByCode(CLIENT_3.getCode(), MBClientType.class)).thenReturn(CLIENT_SPY);
        when(typeService.create(MBClientType.class)).thenReturn(CLIENT_1);
        when(typeService.save(CLIENT_SPY)).thenReturn(true);
        when(typeService.save(CLIENT_1)).thenReturn(true);

        service.updateClient(CLIENT_3);
        verify(CLIENT_SPY).setName(CLIENT_3.getName());

        service.updateClient(CLIENT_1);
        verify(typeService).create(any());
        verify(typeService,times(2)).save(any());
    }

    @Test
    void deleteCategories() {
        MBClientType CLIENT_3_SPY = spy(CLIENT_3);
        MBClientType CLIENT_2_SPY = spy(CLIENT_2);

        when(typeService.findItemByCode(CLIENT_3_SPY.getCode(), MBClientType.class)).thenReturn(CLIENT_3_SPY);
        when(typeService.findItemByCode(CLIENT_2_SPY.getCode(), MBClientType.class)).thenReturn(CLIENT_2_SPY);
        when(typeService.findItemByCode(CLIENT_1.getCode(), MBClientType.class)).thenThrow(NotFoundException.class);
        when(typeService.save(any())).thenReturn(true);

        service.deleteMBItem(Set.of(CLIENT_1.getCode(),CLIENT_2_SPY.getCode(),CLIENT_3_SPY.getCode()));
        verify(CLIENT_2_SPY).setActive(false);
        verify(CLIENT_3_SPY).setActive(false);
        verify(typeService,times(2)).save(any());
    }
}