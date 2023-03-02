package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBFacilityService;
import bi.manager.core.types.MBFacilityType;
import bi.manager.facade.converters.facility.FacilityMapper;
import bi.manager.facade.converters.facility.FullFacilityMapper;
import bi.manager.facade.data.MBFacilityData;
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
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultMBFacilityFacadeTest {
    final static MBFacilityData DATA = new MBFacilityData();
    final static MBFacilityType TYPE = new MBFacilityType();

    @InjectMocks
    DefaultMBFacilityFacade facilityFacade;
    @Mock
    MBFacilityService facilityService;
    @Mock
    FullFacilityMapper fullFacilityMapper;
    @Mock
    FacilityMapper facilityMapper;
    @Mock
    TypeService typeService;

    @BeforeEach
    void setUp() {

        DATA.setName("a");
        DATA.setAlias("b");
        DATA.setActive(true);

        TYPE.setName("a");
        TYPE.setAlias("b");
        TYPE.setActive(true);
    }

    @Test
    void getAllFacilities() {
        when(facilityMapper.facilitiesToData(List.of(TYPE))).thenReturn(List.of(DATA));
        when(facilityService.getAllFacilities()).thenReturn(List.of(TYPE));
        Collection<MBFacilityData> actual = facilityFacade.getAllFacilities(false);

        assertEquals(1, actual.size());
        assertTrue(actual.contains(DATA));
    }

    @Test
    void getFacilityByCode() {
        when(facilityService.getFacilityByCode("a")).thenReturn(TYPE);
        when(fullFacilityMapper.facilityToData(TYPE)).thenReturn(DATA);
        MBFacilityData actual = facilityFacade.getFacilityByCode("a", true);
        assertEquals(actual, DATA);

    }

    @Test
    void deleteFacilities() {
        doNothing().when(facilityService).deleteFacilities(Set.of("a"));
        facilityFacade.deleteFacilities(Set.of("a"));
        verify(facilityService, times(1)).deleteFacilities(Set.of("a"));
    }

    @Test
    void updateFacility() {
        when(facilityService.getFacilityByCode(DATA.getCode())).thenReturn(TYPE);

        TYPE.setAddress("muyinga");
        DATA.setAddress("muyinga");
        facilityFacade.updateFacility(DATA);
        verify(facilityService, atLeastOnce()).updateFacility(TYPE);
    }

    @Test
    void createFacility(){
        when(typeService.create(MBFacilityType.class)).thenReturn(TYPE);
        when(facilityService.getFacilityByCode(DATA.getCode())).thenThrow(NotFoundException.class);
        facilityFacade.updateFacility(DATA);
        verify(facilityService, atLeastOnce()).createFacility(TYPE);
    }
}