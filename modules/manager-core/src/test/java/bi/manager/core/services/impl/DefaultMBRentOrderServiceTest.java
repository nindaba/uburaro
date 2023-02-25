package bi.manager.core.services.impl;

import bi.manager.core.repositories.MBOrderRepository;
import bi.manager.core.services.MBClientService;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBRentContractType;
import bi.manager.core.types.client.MBRentOrderType;
import bi.uburaro.core.repositories.GeneratedKeyRepository;
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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static bi.manager.core.services.impl.DefaultMBRentOrderService.RENT_ORDER_PREFIX;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultMBRentOrderServiceTest {


    static final MBFacilityType FACILITY_TYPE = new MBFacilityType();
    static final MBRentPropertyType RENT = new MBRentPropertyType();
    static final MBClientType CLIENT = new MBClientType();
    static final MBRentOrderType ORDER = new MBRentOrderType();
    static final MBRentContractType CONTRACT = new MBRentContractType();
    static MBRentOrderType ORDER_SPY;
    static MBClientType CLIENT_SPY;
    static MBRentPropertyType RENT_SPY;
    static long COST;

    @InjectMocks
    DefaultMBRentOrderService service;

    @Mock
    MBClientService clientService;
    @Mock
    TypeService typeService;
    @Mock
    Environment environment;
    @Mock
    GeneratedKeyRepository generatedKeyRepository;
    @Mock
    MBOrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        FACILITY_TYPE.setCode("facility");
        FACILITY_TYPE.setRents(Set.of(RENT));

        CLIENT.setCode("cl1");
        CLIENT.setFacility(FACILITY_TYPE);

        RENT.setCode("1000");
        RENT.setCost(1000l);
        RENT.setName("rent");
        RENT.setActive(true);
        RENT.setFacility(FACILITY_TYPE);
        RENT.setAddress("muyinga");
        RENT.setUnit(30);
        RENT.setContracts(Set.of(CONTRACT));
        RENT.setCurrentContract(CONTRACT);

        ORDER.setFrom(LocalDate.now().minusMonths(2));
        ORDER.setTo(LocalDate.now());
        ORDER.setUnit(30);
        ORDER.setClient(CLIENT);
        ORDER.setOrderNumber("number");
        ORDER.setRentProperty(RENT);

        CONTRACT.setCostPerUnit(100);
        CONTRACT.setUnit(30);


        ORDER_SPY = spy(ORDER);
        CLIENT_SPY = spy(CLIENT);
        RENT_SPY = spy(RENT);

        COST = ORDER.getCost() * RENT.getUnit();
    }

    @Test
    void placeOrder() {
        GeneratedKey key = new GeneratedKey();
        key.setGeneratedValue(23l);
        when(clientService.getClientByCode(CLIENT.getCode())).thenReturn(CLIENT_SPY);
        when(typeService.findItemByCode(RENT.getCode(), MBRentPropertyType.class)).thenReturn(RENT_SPY);
        when(environment.getProperty(RENT_ORDER_PREFIX, String.class, "RO-")).thenReturn("RO-");
        when(generatedKeyRepository.save(new GeneratedKey())).thenReturn(key);
        when(typeService.create(MBRentOrderType.class)).thenReturn(ORDER_SPY);

        service.placeOrder(ORDER);

        verify(CLIENT_SPY).setTotalDebt(CLIENT.getTotalDebt() - COST);
        verify(RENT_SPY).setTotalIncome(RENT.getTotalIncome() + COST);
        verify(ORDER_SPY).setQuantity((int) (ChronoUnit.DAYS.between(ORDER.getFrom(), ORDER.getTo()) / RENT.getUnit()));
        verify(ORDER_SPY).setUnitCharged(ORDER.getUnitCharged());
        verify(ORDER_SPY).setRentProperty(RENT_SPY);
        verify(ORDER_SPY).setClient(CLIENT_SPY);
        verify(ORDER_SPY).setTotalUnitCharged(ORDER_SPY.getTotalUnitCharged() + ORDER.getUnitCharged());
        verify(typeService).save(ORDER_SPY);
    }

    @Test
    void deleteOrder() {
        ORDER_SPY.setClient(CLIENT_SPY);
        ORDER_SPY.setRentProperty(RENT_SPY);

        when(orderRepository.findByOrderNumber(ORDER.getOrderNumber())).thenReturn(ORDER_SPY);
        doNothing().when(typeService).delete(ORDER_SPY);
        when(typeService.save(CLIENT_SPY)).thenReturn(true);
        when(typeService.save(RENT_SPY)).thenReturn(true);

        service.deleteOrder(Set.of(ORDER.getOrderNumber()));
        verify(CLIENT_SPY).setTotalDebt(CLIENT.getTotalDebt() + COST);
        verify(RENT_SPY).setTotalIncome(RENT.getTotalIncome() - COST);
    }
}