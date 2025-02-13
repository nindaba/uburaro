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
        CONTRACT.setNextOrderDate(CONTRACT.getFrom());
        CONTRACT.setTo(LocalDate.now());
        CONTRACT.setClient(CLIENT);
        CONTRACT.setRentProperty(RENT);
        CONTRACT.setUnit(30);

        RENT.setCurrentContract(CONTRACT);

    }


    @Test
    void shouldCreateOrderFromContract() {

        // Given
        var expectedOrder = new MBRentOrderType();
        expectedOrder.setFrom(CONTRACT.getNextOrderDate());
        expectedOrder.setTo(CONTRACT.getNextOrderDate().plusMonths(1));
        expectedOrder.setClient(CLIENT);
        expectedOrder.setRentProperty(RENT);
        expectedOrder.setContract(CONTRACT);
        expectedOrder.setOrderDate(CONTRACT.getNextOrderDate());

        // When
        var actualOrder = generate.createOrder(CONTRACT);

        // Then
        assertEquals(expectedOrder.getFrom(), CONTRACT.getNextOrderDate(), "Order should start from contract's next order date");
        assertEquals(expectedOrder.getTo(), CONTRACT.getNextOrderDate().plusMonths(1), "Order should end at contract's new next order date");
        assertEquals(expectedOrder.getClient(), actualOrder.getClient(), "Order should have same client as contract");
        assertEquals(expectedOrder.getRentProperty(), actualOrder.getRentProperty(), "Order should have same property as contract");
        assertEquals(expectedOrder.getOrderDate(), CONTRACT.getNextOrderDate(), "Order date should be the contract's next orderdate");
    }

    @Test
    void shouldScheduleInitialOrder() {
        doNothing().when(service).placeOrder(any());
        generate.createOrdersAndSchedule(CONTRACT);
        assertEquals(LocalDate.now(), CONTRACT.getNextOrderDate(), "Next order date should be set to current date");
    }

    @Test
    void shouldScheduleOrderWithFutureEndDate() {
        var nextOrderDate = LocalDate.of(2025,02,6);
        doNothing().when(service).placeOrder(any());
        CONTRACT.setNextOrderDate(nextOrderDate);
        CONTRACT.setTo(nextOrderDate.plusMonths(12));
        generate.createOrdersAndSchedule(CONTRACT);
        assertEquals(nextOrderDate.plus(1, ChronoUnit.MONTHS), CONTRACT.getNextOrderDate(), "Next order date should be set to contract end date");
    }

    @Test
    void shouldScheduleOrderAtContractEnd() {
        CONTRACT.setNextOrderDate(CONTRACT.getTo());
        generate.createOrdersAndSchedule(CONTRACT);
        assertEquals(CONTRACT.getTo(), CONTRACT.getNextOrderDate(), "Next order date should remain at contract end date");
    }

    @Test
    void shouldTerminateContractWhenOrderDateEqualsEndDate() {
        CONTRACT.setNextOrderDate(CONTRACT.getTo());
        generate.createOrdersAndSchedule(CONTRACT);
        verify(RENT).setCurrentContract(null);
    }




}
