package bi.manager.core.services.impl;

import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBInventoryType;
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
class DefaultMBInventoryServiceTest {

    static final MBCategoryType CATEGORY = new MBCategoryType();

    static final MBInventoryType INVENTORY_1 = new MBInventoryType();
    static final MBInventoryType INVENTORY_2 = new MBInventoryType();
    static final MBInventoryType INVENTORY_3 = new MBInventoryType();


    @InjectMocks
    DefaultMBInventoryService service;

    @Mock
    TypeService typeService;

    @BeforeEach
    void setUp() {
        CATEGORY.setCode("c1");
        CATEGORY.setActive(true);

        INVENTORY_1.setCode("1");
        INVENTORY_1.setName("one");
        INVENTORY_1.setActive(true);
        INVENTORY_1.setCategory(CATEGORY);

        INVENTORY_2.setCode("2");
        INVENTORY_2.setName("two");
        INVENTORY_2.setQuantity(10);
        INVENTORY_2.setActive(false);

        INVENTORY_3.setCode("3");
        INVENTORY_3.setName("three");
        INVENTORY_3.setQuantity(7);
        INVENTORY_3.setActive(true);

        CATEGORY.setInventories(Set.of(INVENTORY_1, INVENTORY_2, INVENTORY_3));
    }

    @Test
    void getAllInventoriesByCategory() {
        when(typeService.findItemByCode(CATEGORY.getCode(), MBCategoryType.class)).thenReturn(CATEGORY);
        Collection<MBInventoryType> actual = service.getInventoriesByCategory(CATEGORY.getCode());
        assertEquals(2, actual.size());
    }

    @Test
    void getInventoryByCode() {
        when(typeService.findItemByCode(INVENTORY_2.getCode(), MBInventoryType.class)).thenReturn(INVENTORY_2);
        MBInventoryType actual = service.getInventoryByCode(INVENTORY_2.getCode());
        assertEquals(INVENTORY_2, actual);
    }

    @Test
    void updateInventory() {
        MBInventoryType INVENTORY_2_SPY = spy(INVENTORY_2);
        when(typeService.findItemByCode(INVENTORY_2.getCode(), MBInventoryType.class)).thenReturn(INVENTORY_2_SPY);
        when(typeService.findItemByCode(INVENTORY_1.getCode(), MBInventoryType.class)).thenThrow(NotFoundException.class);
        when(typeService.findItemByCode(CATEGORY.getCode(), MBCategoryType.class)).thenReturn(CATEGORY);
        when(typeService.save(INVENTORY_1)).thenReturn(true);
        when(typeService.save(INVENTORY_2)).thenReturn(true);
        when(typeService.create(MBInventoryType.class)).thenReturn(new MBInventoryType());

        service.updateInventory(INVENTORY_1);
        verify(typeService).save(INVENTORY_1);
        verify(typeService).create(MBInventoryType.class);

        INVENTORY_2.setCode("2");
        INVENTORY_2.setName("2");
        service.updateInventory(INVENTORY_2);
        verify(typeService).save(INVENTORY_2);
        verify(INVENTORY_2_SPY,times(0)).setCode(INVENTORY_2.getCode());
        verify(INVENTORY_2_SPY,times(1)).setName(INVENTORY_2.getName());

    }

    @Test
    void deleteInventory() {
        when(typeService.findItemByCode(INVENTORY_2.getCode(), MBInventoryType.class)).thenReturn(INVENTORY_2);
        when(typeService.findItemByCode(INVENTORY_3.getCode(), MBInventoryType.class)).thenReturn(INVENTORY_3);
        doThrow(NotFoundException.class).when(typeService).findItemByCode(INVENTORY_1.getCode(), MBInventoryType.class);

        when(typeService.save(INVENTORY_2)).thenReturn(true);
        when(typeService.save(INVENTORY_3)).thenReturn(true);

        service.deleteInventory(Set.of(INVENTORY_2.getCode(), INVENTORY_3.getCode(), INVENTORY_1.getCode()));
        verify(typeService, times(1)).save(INVENTORY_2);
        verify(typeService, times(1)).save(INVENTORY_3);
        verify(typeService, times(0)).save(INVENTORY_1);
    }
}