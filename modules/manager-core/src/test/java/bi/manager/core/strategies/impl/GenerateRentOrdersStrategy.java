package bi.manager.core.strategies.impl;

import bi.manager.core.repositories.MBRentContractRepository;
import bi.manager.core.services.MBRentOrderService;
import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBRentContractType;
import bi.manager.core.types.client.MBRentOrderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultGenerateRentOrdersStrategyTest {
    static final MBRentContractType CONTRACT = new MBRentContractType();
    static final MBRentOrderType ORDER =  new MBRentOrderType();
    @InjectMocks
    DefaultGenerateRentOrdersStrategy generate;
    @Mock
    MBRentOrderService service;
    @Spy
    MBClientType CLIENT;
    @Spy
    MBRentPropertyType RENT;

    @BeforeEach
    void setUp() {
        CONTRACT.setCode("Code");
        CONTRACT.setFrom(LocalDate.now().minus(2, ChronoUnit.MONTHS));
        CONTRACT.setNextOrderDate(LocalDate.now().minus(1, ChronoUnit.MONTHS));
        CONTRACT.setTo(LocalDate.now());
        CONTRACT.setClient(CLIENT);
        CONTRACT.setRentProperty(RENT);
        CONTRACT.setUnit(30);

    }

    @Test
    void createOrder() {
        ORDER.setFrom(CONTRACT.getFrom());
        ORDER.setTo(CONTRACT.getNextOrderDate());
        ORDER.setClient(CLIENT);
        ORDER.setRentProperty(RENT);
        ORDER.setOrderDate(LocalDate.now());

        doNothing().when(service).placeOrder(ORDER);
        generate.createOrder(CONTRACT);
    }

    @Test
    void scheduleNextOrderDate() {
        generate.scheduleNextOrderDate(CONTRACT);
        assertEquals(LocalDate.now(),CONTRACT.getNextOrderDate());

        CONTRACT.setTo(LocalDate.now().plus(15,ChronoUnit.DAYS));
        generate.scheduleNextOrderDate(CONTRACT);
        assertEquals(LocalDate.now().plus(15, ChronoUnit.DAYS),CONTRACT.getNextOrderDate());

        //terminate contract when order date was equal to getTo()
        CONTRACT.setNextOrderDate(CONTRACT.getTo());
        generate.scheduleNextOrderDate(CONTRACT);
        verify(RENT).setCurrentContract(null);
    }
}