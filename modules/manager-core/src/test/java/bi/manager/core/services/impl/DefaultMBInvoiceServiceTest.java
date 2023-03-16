package bi.manager.core.services.impl;

import bi.manager.core.repositories.MBInvoiceRepository;
import bi.manager.core.repositories.MBOrderRepository;
import bi.manager.core.services.MBCapitalService;
import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.core.types.client.MBOrderType;
import bi.manager.core.types.client.MBRentOrderType;
import bi.manager.core.types.enums.MBPaymentModeEnum;
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

import java.util.Set;

import static bi.manager.core.services.impl.DefaultMBInvoiceService.INVOICE_NUMBER_PREFIX;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultMBInvoiceServiceTest {
    static final MBInvoiceType INVOICE = new MBInvoiceType();
    static final MBFacilityType FACILITY = new MBFacilityType();
    static final MBClientType CLIENT = new MBClientType();
    static final MBRentOrderType ORDER = new MBRentOrderType();
    static final MBCapitalEntryType ENTRY = new MBCapitalEntryType();
    static final MBCapitalType CAPITAL = new MBCapitalType();
    static MBOrderType ORDER_SPY;
    static MBInvoiceType INVOICE_SPY;
    static MBClientType CLIENT_SPY;

    @InjectMocks
    DefaultMBInvoiceService service;
    @Mock
    TypeService typeService;
    @Mock
    GeneratedKeyRepository generatedKeyRepository;
    @Mock
    Environment environment;
    @Mock
    MBOrderRepository orderRepository;
    @Mock
    MBCapitalService capitalService;
    @Mock
    MBInvoiceRepository invoiceRepository;

    @BeforeEach
    void setUp() {
        FACILITY.setCode("facility");

        CLIENT.setCode("cl1");
        CLIENT.setFacility(FACILITY);
        CLIENT.setTotalDebt(-50l);


        ORDER.setOrderNumber("number");

        ENTRY.setAmount(50l);

        INVOICE.setCapitalEntry(ENTRY);
        INVOICE.setAmount(100l);
        INVOICE.setClient(CLIENT);
        INVOICE.setOrders(Set.of(ORDER));
        INVOICE.setDescription("desc");
        INVOICE.setPaymentMode(MBPaymentModeEnum.BANK);

        INVOICE_SPY = spy(INVOICE);
        ORDER_SPY = spy(ORDER);
        CLIENT_SPY = spy(CLIENT);
    }

    @Test
    void updateInvoice() {
        INVOICE.setInvoiceNumber("one");
        INVOICE_SPY.setClient(CLIENT_SPY);

        when(typeService.findItemByCode(INVOICE.getCode(), MBInvoiceType.class)).thenReturn(INVOICE_SPY);
        doNothing().when(capitalService).addCapital(INVOICE_SPY);

        service.updateInvoice(INVOICE);

        verify(INVOICE_SPY).setAmount(INVOICE.getAmount());
        verify(INVOICE_SPY).setPaymentMode(INVOICE.getPaymentMode());
        verify(INVOICE_SPY).setDescription(INVOICE.getDescription());
        verify(CLIENT_SPY).setTotalDebt(50);
        verify(typeService).save(INVOICE_SPY);

    }

    @Test
    void createInvoice() {
        INVOICE_SPY.setCapitalEntry(null);
        GeneratedKey key = new GeneratedKey();
        key.setGeneratedValue(23l);
        when(generatedKeyRepository.save(new GeneratedKey())).thenReturn(key);
        when(environment.getProperty(INVOICE_NUMBER_PREFIX, "IN-")).thenReturn("IN-");
        when(orderRepository.findByOrderNumber(ORDER.getOrderNumber())).thenReturn(ORDER_SPY);
        when(typeService.findItemByCode(CLIENT.getCode(), MBClientType.class)).thenReturn(CLIENT_SPY);
        doNothing().when(capitalService).addCapital(INVOICE_SPY);
        when(typeService.create(MBInvoiceType.class)).thenReturn(INVOICE_SPY);

        service.updateInvoice(INVOICE);

        verify(INVOICE_SPY).setInvoiceNumber("IN-23");
        verify(INVOICE_SPY).setAmount(INVOICE.getAmount());
        verify(INVOICE_SPY).setPaymentMode(INVOICE.getPaymentMode());
        verify(INVOICE_SPY).setClient(CLIENT_SPY);
        verify(INVOICE_SPY).setDescription(INVOICE.getDescription());
        verify(ORDER_SPY).setInvoice(INVOICE_SPY);
        verify(ORDER_SPY).setPaid(true);
        verify(CLIENT_SPY).setTotalDebt(CLIENT.getTotalDebt() + INVOICE.getAmount());
        verify(typeService).save(INVOICE_SPY);
    }

    @Test
    void deleteInvoice() {
        MBCapitalType CAPITAL_SPY = spy(CAPITAL);

        INVOICE.setInvoiceNumber("one");
        INVOICE.setClient(CLIENT_SPY);
        ENTRY.setCapital(CAPITAL_SPY);
        CAPITAL_SPY.setCurrentValue(0);
        CLIENT_SPY.setTotalDebt(0);

        when(invoiceRepository.findByInvoiceNumber(INVOICE.getInvoiceNumber())).thenReturn(INVOICE);
        doNothing().when(typeService).delete(INVOICE);
        when(typeService.save(CAPITAL_SPY)).thenReturn(true);
        when(typeService.save(ENTRY)).thenReturn(true);
        when(typeService.save(CLIENT_SPY)).thenReturn(true);

        service.deleteInvoice(Set.of(INVOICE.getInvoiceNumber()));

        verify(CLIENT_SPY).setTotalDebt(-INVOICE.getAmount());
        verify(CAPITAL_SPY).setCurrentValue(-INVOICE.getAmount());
    }
}