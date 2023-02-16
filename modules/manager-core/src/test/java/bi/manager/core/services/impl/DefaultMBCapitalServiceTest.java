package bi.manager.core.services.impl;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBFacilityType;
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
    void getCapitalEntries() {
        when(typeService.findItemByCode(FACILITY_CODE, MBFacilityType.class)).thenReturn(FACILITY_TYPE);

        //Testing with all the date range, so it should return all 3
        CALENDAR.set(2023,1,3);
        Date to = CALENDAR.getTime();
        CALENDAR.set(2023,1,1);
        Date from = CALENDAR.getTime();
        Collection<MBCapitalEntryType> entries = service.getCapitalEntries(FACILITY_CODE, from, to);
        assertEquals(3, entries.size());

        //Testing for lower dates so it should return 2
        CALENDAR.set(2023,1,2);
        to = CALENDAR.getTime();
        CALENDAR.set(2023,1,1);
        from = CALENDAR.getTime();
        entries = service.getCapitalEntries(FACILITY_CODE, from, to);
        assertEquals(3, entries.size());

        //Testing the upper bound the result should be 1
        CALENDAR.set(2023,1,3);
        to = CALENDAR.getTime();
        CALENDAR.set(2023,1,3);
        from = CALENDAR.getTime();
        entries = service.getCapitalEntries(FACILITY_CODE, from, to);
        assertEquals(3, entries.size());
    }
}
