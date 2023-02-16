package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBInventoryService;
import bi.manager.core.types.MBInventoryType;
import bi.manager.facade.converters.inventory.FullInventoryMapper;
import bi.manager.facade.converters.inventory.InventoryMapper;
import bi.manager.facade.data.MBCategoryData;
import bi.manager.facade.data.MBFacilityData;
import bi.manager.facade.data.MBInventoryData;
import bi.manager.facade.facades.MBCategoryFacade;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultMBInventoryFacadeTest {

    @InjectMocks
    DefaultMBInventoryFacade facade;
    @Mock
    MBInventoryService service;
    @Mock
    InventoryMapper mapper;
    @Mock
    FullInventoryMapper fullMapper;
    @Mock
    MBCategoryFacade categoryFacade;

    static final MBCategoryData CATEGORY = new MBCategoryData();
    static final MBCategoryData CATEGORY_2 = new MBCategoryData();

    static final MBFacilityData FACILITY_DATA = new MBFacilityData();

    static final MBInventoryData INVENTORY_1 = new MBInventoryData();
    static final MBInventoryData INVENTORY_2 = new MBInventoryData();
    static final MBInventoryData INVENTORY_3 = new MBInventoryData();

    static final MBInventoryType INVENTORY_TYPE_1 = new MBInventoryType();
    static final MBInventoryType INVENTORY_TYPE_2 = new MBInventoryType();
    public static final List<MBInventoryType> INVENTORY_TYPES = List.of(INVENTORY_TYPE_1, INVENTORY_TYPE_2);

    @BeforeEach
    void setUp() {
        FACILITY_DATA.setName("a");
        FACILITY_DATA.setAlias("b");
        FACILITY_DATA.setActive(true);

        CATEGORY.setCode("c1");
        CATEGORY.setActive(true);

        CATEGORY_2.setCode("c1");
        CATEGORY_2.setActive(true);

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

        CATEGORY.setInventories(Set.of(INVENTORY_1, INVENTORY_3));
        CATEGORY_2.getInventories().add(INVENTORY_2);

        INVENTORY_1.setCode("1");
        INVENTORY_1.setName("one");
        INVENTORY_1.setActive(true);

        INVENTORY_2.setCode("2");
        INVENTORY_2.setName("two");
        INVENTORY_2.setQuantity(10);
        INVENTORY_2.setActive(true);
    }

    @Test
    void getInventoriesByCategory() {
        when(service.getInventoriesByCategory(CATEGORY.getCode())).thenReturn(INVENTORY_TYPES);
        when(fullMapper.inventoriesToData(INVENTORY_TYPES)).thenReturn(CATEGORY.getInventories());
        when(mapper.inventoriesToData(INVENTORY_TYPES)).thenReturn(CATEGORY.getInventories());
        Collection<MBInventoryData> actual = facade.getInventoriesByCategory(CATEGORY.getCode(), false);

        assertEquals(2,actual.size());
        verify(mapper).inventoriesToData(INVENTORY_TYPES);

        facade.getInventoriesByCategory(CATEGORY.getCode(), true);
        verify(fullMapper).inventoriesToData(INVENTORY_TYPES);

    }

    @Test
    void getInventoryByCode() {
        when(service.getInventoryByCode(INVENTORY_1.getCode())).thenReturn(INVENTORY_TYPE_1);
        when(fullMapper.inventoryToData(INVENTORY_TYPE_1)).thenReturn(INVENTORY_1);
        when(mapper.inventoryToData(INVENTORY_TYPE_1)).thenReturn(INVENTORY_1);

        MBInventoryData actual = facade.getInventoryByCode(INVENTORY_1.getCode(), false);
        assertEquals(INVENTORY_1,actual);
        verify(mapper).inventoryToData(INVENTORY_TYPE_1);

        facade.getInventoryByCode(INVENTORY_1.getCode(), true);
        verify(fullMapper).inventoryToData(INVENTORY_TYPE_1);
    }

    @Test
    void updateInventory() {
        when(mapper.inventoryToType(INVENTORY_1)).thenReturn(INVENTORY_TYPE_1);
        doNothing().when(service).updateInventory(INVENTORY_TYPE_1);
        facade.updateInventory(INVENTORY_1);
        verify(service).updateInventory(INVENTORY_TYPE_1);
    }

    @Test
    void createInventory() {
        when(mapper.inventoryToType(INVENTORY_1)).thenReturn(INVENTORY_TYPE_1);
        doNothing().when(service).updateInventory(INVENTORY_TYPE_1);
        facade.updateInventory(INVENTORY_1);
        verify(service).updateInventory(INVENTORY_TYPE_1);
    }

    @Test
    void deleteInventory() {
        Set<String> codes = Set.of(INVENTORY_1.getCode(), INVENTORY_3.getCode());
        doNothing().when(service).deleteInventory(codes);
        facade.deleteInventory(codes);
        verify(service).deleteInventory(codes);
    }

    @Test
    void getInventoryByFacilityCode() {
        when(categoryFacade.getCategoriesByFacilityCode(FACILITY_DATA.getCode(),true)).thenReturn(List.of(CATEGORY,CATEGORY_2));
        INVENTORY_2.setActive(false);
        Collection<MBInventoryData> actual = facade.getInventoryByFacilityCode(FACILITY_DATA.getCode());
        verify(categoryFacade).getCategoriesByFacilityCode(FACILITY_DATA.getCode(),true);
        assertEquals(2,actual.size());

    }
}