package bi.manager.core.services.impl;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.core.types.enums.MBEntryEnum;
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

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultMBCapitalServiceTest {

    static final MBCapitalType CAPITAL_TYPE = new MBCapitalType();
    static final MBCapitalEntryType CAPITAL_ENTRY_TYPE1 = new MBCapitalEntryType();
    static final MBCapitalEntryType CAPITAL_ENTRY_TYPE2 = new MBCapitalEntryType();
    static final MBCapitalEntryType CAPITAL_ENTRY_TYPE3 = new MBCapitalEntryType();
    static final String FACILITY_CODE = "FACILITY";
    static final MBInvoiceType INVOICE =  new MBInvoiceType();

    static final MBFacilityType FACILITY_TYPE = new MBFacilityType();
    static final Calendar CALENDAR = Calendar.getInstance();
    @InjectMocks
    DefaultMBCapitalService service;
    @Mock
    TypeService typeService;

    @BeforeEach
    void setUp() {
        CAPITAL_TYPE.setCurrentValue(0);
        CAPITAL_ENTRY_TYPE1.setAmount(1200l);
        CAPITAL_ENTRY_TYPE1.setEntryType(MBEntryEnum.EXTERNAL);
        CAPITAL_ENTRY_TYPE2.setAmount(1200l);
        CAPITAL_ENTRY_TYPE2.setEntryType(MBEntryEnum.INTERNAL);
        CAPITAL_ENTRY_TYPE3.setAmount(1200l);
        CAPITAL_ENTRY_TYPE3.setEntryType(MBEntryEnum.EXPENSE);
        CALENDAR.set(2023,1,1);
        CAPITAL_ENTRY_TYPE1.setDateModified(CALENDAR.getTime());
        CALENDAR.set(2023,1,2);
        CAPITAL_ENTRY_TYPE2.setDateModified(CALENDAR.getTime());
        CALENDAR.set(2023,1,3);
        CAPITAL_ENTRY_TYPE3.setDateModified(CALENDAR.getTime());
        CAPITAL_TYPE.setEntries(Set.of(CAPITAL_ENTRY_TYPE1, CAPITAL_ENTRY_TYPE2, CAPITAL_ENTRY_TYPE3));
        FACILITY_TYPE.setCapital(CAPITAL_TYPE);
        MBClientType mbClientType = new MBClientType();
        mbClientType.setFacility(FACILITY_TYPE);
        INVOICE.setClient(mbClientType);
        INVOICE.setAmount(2400l);
    }

    @Test
    void addCapital() {
        when(typeService.findItemByCode(FACILITY_CODE, MBFacilityType.class)).thenReturn(FACILITY_TYPE);
        when(typeService.create(MBCapitalEntryType.class)).thenReturn(CAPITAL_ENTRY_TYPE1);
        when(typeService.save(any())).thenReturn(true);
        service.addCapital(FACILITY_CODE,1200l, MBEntryEnum.EXTERNAL);
        verify(typeService).create(MBCapitalEntryType.class);
        verify(typeService).save(CAPITAL_ENTRY_TYPE1);

    }

    @Test
    void addInvoiceCapital() {
        CAPITAL_TYPE.setCurrentValue(1200l*3);
        MBCapitalEntryType ENTRY = spy(CAPITAL_ENTRY_TYPE1);
        when(typeService.save(any())).thenReturn(true);
        when(typeService.create(MBCapitalEntryType.class)).thenReturn(ENTRY);
        service.addCapital(INVOICE);
        verify(ENTRY).setInvoice(INVOICE);
        verify(ENTRY).setAmount(INVOICE.getAmount());
        verify(ENTRY).setEntryType(MBEntryEnum.INTERNAL);
        verify(ENTRY).setCapital(CAPITAL_TYPE);
        assertEquals(1200l*5,CAPITAL_TYPE.getCurrentValue());
    }
    @Test
    void updateInvoiceCapital() {
        CAPITAL_TYPE.setCurrentValue(1200l*4);
        MBCapitalEntryType ENTRY = spy(CAPITAL_ENTRY_TYPE1);
        when(typeService.save(any())).thenReturn(true);
        INVOICE.setCapitalEntry(ENTRY);
        service.addCapital(INVOICE);
        verify(ENTRY).setAmount(INVOICE.getAmount());
        assertEquals(1200l*5,CAPITAL_TYPE.getCurrentValue());
    }
}
