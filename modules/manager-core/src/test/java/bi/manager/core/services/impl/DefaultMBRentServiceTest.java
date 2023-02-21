package bi.manager.core.services.impl;

import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.MBRentPropertyType;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultMBRentServiceTest {
    static final MBFacilityType FACILITY_TYPE = new MBFacilityType();
    static final MBRentPropertyType RENT = new MBRentPropertyType();
    static final MBClientType CLIENT = new MBClientType();
    @InjectMocks
    DefaultMBRentService service;
    @Mock
    TypeService typeService;


    @BeforeEach
    void setUp() {
        FACILITY_TYPE.setCode("facility");
        FACILITY_TYPE.setRents(Set.of(RENT));

        CLIENT.setCode("cl1");
        CLIENT.setName("name1");
        CLIENT.setActive(true);
        CLIENT.setFacility(FACILITY_TYPE);

        RENT.setCode("1000");
        RENT.setCost(1000l);
        RENT.setName("rent");
        RENT.setCurrentClient(CLIENT);
        RENT.setActive(true);
        RENT.setFacility(FACILITY_TYPE);
        RENT.setAddress("muyinga");
        RENT.setUnit("unit");

        when(typeService.findItemByCode(FACILITY_TYPE.getCode(), MBFacilityType.class)).thenReturn(FACILITY_TYPE);
    }

    @Test
    void getRentsByFacilityCode() {
        Collection<MBRentPropertyType> actual = service.getRentsByFacilityCode(FACILITY_TYPE.getCode());
        assertEquals(1,actual.size());
    }

    @Test
    void updateRents() {
        MBRentPropertyType RENT_SPY = spy(new MBRentPropertyType());
        when(typeService.save(RENT_SPY)).thenReturn(true);
        when(typeService.findItemByCode(RENT.getCode(),MBRentPropertyType.class)).thenReturn(RENT_SPY);
        when(typeService.findItemByCode(CLIENT.getCode(), MBClientType.class)).thenReturn(CLIENT);

        service.updateRent(RENT);
        verify(typeService).save(RENT_SPY);

        verify(RENT_SPY).setCode("1000");
        verify(RENT_SPY).setCost(1000l);
        verify(RENT_SPY).setName("rent");
        verify(RENT_SPY).setCurrentClient(CLIENT);
        verify(RENT_SPY).setActive(true);
        verify(RENT_SPY).setFacility(FACILITY_TYPE);
        verify(RENT_SPY).setAddress("muyinga");
        verify(RENT_SPY).setUnit("unit");
    }

    @Test
    void createRent() {
        MBRentPropertyType RENT_SPY = spy(new MBRentPropertyType());
        when(typeService.create(MBRentPropertyType.class)).thenReturn(RENT_SPY);
        when(typeService.save(RENT_SPY)).thenReturn(true);
        when(typeService.findItemByCode(RENT.getCode(),MBRentPropertyType.class)).thenThrow(new NotFoundException("No rent Found"));

        service.updateRent(RENT);
        verify(typeService).save(RENT_SPY);
        verify(RENT_SPY).setActive(true);
        verify(RENT_SPY).setFacility(FACILITY_TYPE);
        verify(RENT_SPY).setCode(RENT.getCode());
    }
}