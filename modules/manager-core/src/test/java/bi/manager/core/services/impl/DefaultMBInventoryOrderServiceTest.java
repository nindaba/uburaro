package bi.manager.core.services.impl;

import bi.manager.core.services.MBCapitalService;
import bi.manager.core.services.MBClientService;
import bi.manager.core.services.MBFacilityService;
import bi.manager.core.services.MBInventoryService;
import bi.manager.core.types.*;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.manager.core.types.enums.MBInventoryEntryEnum;
import bi.uburaro.core.repositories.GeneratedKeyRepository;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.GeneratedKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static bi.manager.core.ManagerCoreConstants.INVENTORY_ORDER_PREFIX;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultMBInventoryOrderServiceTest {
    final static MBFacilityType FACILITY = new MBFacilityType();
    static final MBClientType CLIENT = new MBClientType();
    static final MBInventoryType INVENTORY = new MBInventoryType();
    static final MBCategoryType CATEGORY = new MBCategoryType();

    static final MBInventoryOrderType ORDER_1 = new MBInventoryOrderType();
    static final MBInventoryOrderType ORDER_2 = new MBInventoryOrderType();
    static final MBInventoryOrderType ORDER_3 = new MBInventoryOrderType();


    @InjectMocks
    DefaultMBInventoryOrderService service;
    @Mock
    MBFacilityService facilityService;
    @Mock
    MBClientService clientService;
    @Mock
    MBInventoryService inventoryService;
    @Mock
    TypeService typeService;
    @Mock
    Environment environment;
    @Mock
    GeneratedKeyRepository generatedKeyRepository;
    @Mock
    MBCapitalService capitalService;
    @Mock
    ItemRepository itemRepository;


    @BeforeEach
    void setUp() {

        FACILITY.setCode("a");
        FACILITY.setName("a");

        CATEGORY.setCode("c1");
        CATEGORY.setActive(true);
        CATEGORY.setFacility(FACILITY);

        CLIENT.setCode("cl1");
        CLIENT.setActive(true);

        INVENTORY.setCode("1");
        INVENTORY.setActive(true);
        INVENTORY.setCost(100);
        INVENTORY.setQuantity(10);
        INVENTORY.setCategory(CATEGORY);

        ORDER_1.setOrderNumber("number1");
        ORDER_1.setOrderEntry(MBInventoryEntryEnum.SOLD);
        ORDER_1.setInventory(INVENTORY);
        ORDER_1.setQuantity(3);
        ORDER_1.setClient(CLIENT);

        ORDER_2.setOrderNumber("number2");
        ORDER_2.setOrderEntry(MBInventoryEntryEnum.REFILL);
        ORDER_2.setInventory(INVENTORY);
        ORDER_2.setQuantity(2);

        ORDER_3.setOrderNumber("number3");
        ORDER_3.setOrderEntry(MBInventoryEntryEnum.OUT);
        ORDER_3.setQuantity(5);
        ORDER_3.setInventory(INVENTORY);


        INVENTORY.setInventoryOrders(Set.of(ORDER_3, ORDER_2, ORDER_1));
        CATEGORY.setInventories(Set.of(INVENTORY));
        FACILITY.setClients(Set.of(CLIENT));
        FACILITY.setCategories(Set.of(CATEGORY));
        CLIENT.setOrders(Set.of(ORDER_1));
    }

    @Test
    void getOrderByFacilityCode() {
        when(facilityService.getFacilityByCode(FACILITY.getCode())).thenReturn(FACILITY);
        Collection<MBInventoryOrderType> actual = service.getOrderByFacilityCode(FACILITY.getCode());
        assertEquals(3, actual.size());
    }

    @Test
    void getOrderByInventoryCode() {
        when(inventoryService.getInventoryByCode(INVENTORY.getCode())).thenReturn(INVENTORY);
        Collection<MBInventoryOrderType> actual = service.getOrderByInventoryCode(INVENTORY.getCode());
        assertEquals(3, actual.size());
    }

    @Test
    void getOrderByClientCode() {
        when(clientService.getClientByCode(CLIENT.getCode())).thenReturn(CLIENT);
        Collection<MBInventoryOrderType> actual = service.getOrderByClientCode(CLIENT.getCode());
        assertEquals(1, actual.size());
    }

    @Test
    void addOrderForClient() {
        MBInventoryOrderType ORDER_SPY = spy(ORDER_1);
        MBClientType CLIENT_SPY = spy(CLIENT);
        MBInventoryType INVENTORY_SPY = spy(INVENTORY);
        when(typeService.create(MBInventoryOrderType.class)).thenReturn(ORDER_SPY);
        when(generatedKeyRepository.save(new GeneratedKey())).thenReturn(new GeneratedKey());
        when(environment.getProperty(INVENTORY_ORDER_PREFIX, String.class, "IN-")).thenReturn("IN-");
        when(inventoryService.getInventoryByCode(INVENTORY_SPY.getCode())).thenReturn(INVENTORY_SPY);
        when(clientService.getClientByCode(CLIENT_SPY.getCode())).thenReturn(CLIENT_SPY);
        service.placeOrder(ORDER_SPY);

        verify(typeService).save(ORDER_SPY);
        verify(INVENTORY_SPY).setQuantity(INVENTORY.getQuantity() - ORDER_1.getQuantity());
        verify(CLIENT_SPY).setTotalDebt(CLIENT.getTotalDebt() - ORDER_1.getQuantity() * INVENTORY.getCost());
        verify(ORDER_SPY).setClient(CLIENT_SPY);
        verify(capitalService, times(0)).addCapital(INVENTORY.getCost() * ORDER_2.getQuantity(), MBEntryEnum.EXPENSE, FACILITY);

    }

    @Test
    void addInventory() {
        MBInventoryOrderType ORDER_SPY = spy(ORDER_2);
        MBInventoryType INVENTORY_SPY = spy(INVENTORY);

        when(typeService.create(MBInventoryOrderType.class)).thenReturn(ORDER_SPY);
        when(generatedKeyRepository.save(new GeneratedKey())).thenReturn(new GeneratedKey());
        when(environment.getProperty(INVENTORY_ORDER_PREFIX, String.class, "IN-")).thenReturn("IN-");
        when(inventoryService.getInventoryByCode(INVENTORY_SPY.getCode())).thenReturn(INVENTORY_SPY);
        doNothing().when(capitalService).addCapital(INVENTORY.getCost() * ORDER_2.getQuantity(), MBEntryEnum.EXPENSE, FACILITY);

        service.placeOrder(ORDER_SPY);
        verify(typeService).save(ORDER_SPY);
        verify(INVENTORY_SPY).setQuantity(INVENTORY.getQuantity() + ORDER_2.getQuantity());
        verify(capitalService).addCapital(INVENTORY.getCost() * ORDER_2.getQuantity(), MBEntryEnum.EXPENSE, FACILITY);
    }

    /**
     * suppose the inventory was used by the facility
     */
    @Test
    void orderForFacility() {
        MBInventoryOrderType ORDER_SPY = spy(ORDER_3);
        MBInventoryType INVENTORY_SPY = spy(INVENTORY);

        when(typeService.create(MBInventoryOrderType.class)).thenReturn(ORDER_SPY);
        when(generatedKeyRepository.save(new GeneratedKey())).thenReturn(new GeneratedKey());
        when(environment.getProperty(INVENTORY_ORDER_PREFIX, String.class, "IN-")).thenReturn("IN-");
        when(inventoryService.getInventoryByCode(INVENTORY_SPY.getCode())).thenReturn(INVENTORY_SPY);

        service.placeOrder(ORDER_SPY);

        verify(typeService).save(ORDER_SPY);
        verify(INVENTORY_SPY).setQuantity(INVENTORY.getQuantity() - ORDER_3.getQuantity());
    }

    @Test
    void revertClient() {
        MBInventoryOrderType ORDER_SPY = spy(ORDER_1);
        MBClientType CLIENT_SPY = spy(CLIENT);
        when(typeService.save(CLIENT_SPY)).thenReturn(true);
        ORDER_SPY.setClient(CLIENT_SPY);

        service.revertClient(ORDER_SPY);

        verify(typeService).save(CLIENT_SPY);
        verify(CLIENT_SPY).setTotalDebt(CLIENT_SPY.getTotalDebt() + ORDER_1.getQuantity() * INVENTORY.getCost());

    }

    @Test
    void revertStockLevels() {
        MBInventoryType INVENTORY_SPY = spy(INVENTORY);
        when(itemRepository.save(INVENTORY_SPY)).thenReturn(INVENTORY_SPY);
        ORDER_3.setInventory(INVENTORY_SPY);

        service.revertStockLevels(ORDER_3);
        verify(INVENTORY_SPY).setQuantity(INVENTORY.getQuantity() + ORDER_3.getQuantity());
        verify(itemRepository).save(INVENTORY_SPY);
    }

    @Test
    void revertCapital() {
        MBCapitalType CAPITAL = spy(new MBCapitalType());
        MBCapitalEntryType ENTRY = spy(new MBCapitalEntryType());

        CAPITAL.setEntries(Set.of(ENTRY));
        FACILITY.setCapital(CAPITAL);
        ENTRY.setDateModified(new Date());
        ORDER_3.setDateModified(new Date());


        doNothing().when(itemRepository).delete(ENTRY);
        service.revertCapital(ORDER_3);
        verify(CAPITAL).setCurrentValue(-ORDER_3.getCost() * ORDER_3.getQuantity());
        verify(itemRepository).delete(ENTRY);
    }
}