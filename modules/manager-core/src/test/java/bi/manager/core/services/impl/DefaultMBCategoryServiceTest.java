package bi.manager.core.services.impl;

import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBFacilityType;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultMBCategoryServiceTest {
    static final MBCategoryType CATEGORY_1 = new MBCategoryType();
    static final MBCategoryType CATEGORY_2 = new MBCategoryType();
    static final MBCategoryType CATEGORY_3 = new MBCategoryType();
    static final String FACILITY_CODE = "FACILITY";

    static final MBFacilityType FACILITY_TYPE = new MBFacilityType();
    @Mock
    TypeService typeService;
    @InjectMocks
    DefaultMBCategoryService service;

    @BeforeEach
    void setUp() {

        CATEGORY_1.setCode("c1");
        CATEGORY_1.setActive(true);
        CATEGORY_2.setCode("c2");
        CATEGORY_2.setActive(false);
        CATEGORY_3.setCode("c3");
        CATEGORY_3.setActive(true);

        FACILITY_TYPE.setCode(FACILITY_CODE);
        FACILITY_TYPE.setCategories(Set.of(CATEGORY_1, CATEGORY_2, CATEGORY_3));
    }

    @Test
    void getCategoriesByFacilityCode() {
        when(typeService.findItemByCode(FACILITY_CODE, MBFacilityType.class)).thenReturn(FACILITY_TYPE);
        Collection<MBCategoryType> actual = service.getCategoriesByFacilityCode(FACILITY_CODE);
        assertEquals(2, actual.size());
    }

    @Test
    void getCategoryByCode() {
        when(typeService.findItemByCode(CATEGORY_3.getCode(), MBCategoryType.class)).thenReturn(CATEGORY_3);
        MBCategoryType actual = service.getCategoryByCode(CATEGORY_3.getCode());
        assertEquals(CATEGORY_3, actual);
    }

    @Test
    void updateCategory() {
        when(typeService.findItemByCode(CATEGORY_3.getCode(), MBCategoryType.class)).thenReturn(CATEGORY_3);
        doThrow(NotFoundException.class).when(typeService).findItemByCode(CATEGORY_1.getCode(), MBCategoryType.class);
        when(typeService.create(MBCategoryType.class)).thenReturn(CATEGORY_1);
        CATEGORY_2.setCode(CATEGORY_3.getCode());
        CATEGORY_2.setName("CATE");
        service.updateCategory(CATEGORY_2);
        CATEGORY_3.setName("CATE");
        verify(typeService).save(CATEGORY_3);

        service.updateCategory(CATEGORY_1);
        verify(typeService).save(CATEGORY_1);
    }

    @Test
    void deleteCategories() {
        when(typeService.findItemByCode(CATEGORY_1.getCode(), MBCategoryType.class)).thenReturn(CATEGORY_1);
        when(typeService.findItemByCode(CATEGORY_2.getCode(), MBCategoryType.class)).thenReturn(CATEGORY_2);
        doThrow(NotFoundException.class).when(typeService).findItemByCode(CATEGORY_3.getCode(), MBCategoryType.class);

        service.deleteCategories(Set.of(CATEGORY_1.getCode(),CATEGORY_2.getCode(),CATEGORY_3.getCode()));
        verify(typeService).save(CATEGORY_1);
        verify(typeService).save(CATEGORY_2);
        verify(typeService,times(0)).save(CATEGORY_3);
    }
}