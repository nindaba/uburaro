package bi.uburaro.core.services.impl;

import bi.manager.core.repositories.MBFacilityRepository;
import bi.manager.core.services.impl.DefaultMBFacilityService;
import bi.uburaro.core.services.TypeService;
import bi.manager.core.types.MBFacilityType;
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
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultMBFacilityServiceTest {
    final static MBFacilityType TYPE = new MBFacilityType();
    final static MBFacilityType TYPE_NON_ACTIVE = new MBFacilityType();
    @InjectMocks
    DefaultMBFacilityService service;
    @Mock
    TypeService typeService;
    @Mock
    MBFacilityRepository repository;

    @BeforeEach
    void setUp() {
        TYPE.setCode("a");
        TYPE.setName("a");
        TYPE.setAlias("b");
        TYPE.setActive(true);

        TYPE_NON_ACTIVE.setCode("a");
        TYPE_NON_ACTIVE.setName("a");
        TYPE_NON_ACTIVE.setAlias("b");
        TYPE_NON_ACTIVE.setActive(false);
    }

    @Test
    void getFacilityByCode() {
        when(typeService.findItemByCode("a",MBFacilityType.class)).thenReturn(TYPE);
        MBFacilityType actual = service.getFacilityByCode("a");
        assertEquals(TYPE,actual);
    }

    @Test
    void getAllFacilities() {
        when(typeService.findAll(MBFacilityType.class)).thenReturn(List.of(TYPE,TYPE_NON_ACTIVE));
        Collection<MBFacilityType> actual = service.getAllFacilities();
        assertEquals(1,actual.size());
    }

    @Test
    void deleteFacilities() {
        when(typeService.findItemByCode("a",MBFacilityType.class)).thenReturn(TYPE);
        when(typeService.save(TYPE_NON_ACTIVE)).thenReturn(true);
        service.deleteFacilities(Set.of("a"));
        verify(typeService,atLeastOnce()).save(TYPE_NON_ACTIVE);
    }

    @Test
    void createFacility() {
        when(typeService.findItemByCode("a",MBFacilityType.class)).thenReturn(TYPE_NON_ACTIVE);
        when(typeService.save(TYPE)).thenReturn(true);
        service.createFacility(TYPE);
        verify(typeService,atLeastOnce()).save(any(MBFacilityType.class));
    }

    @Test
    void updateFacility() {
        when(typeService.save(TYPE)).thenReturn(true);
        when(typeService.findItemByCode("a",MBFacilityType.class)).thenReturn(TYPE_NON_ACTIVE);
        service.updateFacility(TYPE);
        verify(typeService,atLeastOnce()).save(any(MBFacilityType.class));
    }
}