package bi.manager.core.services.impl;

import bi.manager.core.services.MBClientService;
import bi.manager.core.services.MBFacilityService;
import bi.manager.core.services.MBRentService;
import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBRentContractType;
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
import java.util.Set;

import static bi.manager.core.services.impl.DefaultMBRentContractService.CONTRACT_CODE_PREFIX;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DefaultMBRentContractServiceTest {
    static final MBRentPropertyType RENT = new MBRentPropertyType();
    static final MBClientType CLIENT = new MBClientType();
    static final MBRentContractType CONTRACT = new MBRentContractType();
    static final LocalDate END_OF_CONTRACT = LocalDate.now();

    @InjectMocks
    DefaultMBRentContractService service;

    @Mock
    MBClientService clientService;
    @Mock
    TypeService typeService;
    @Mock
    GeneratedKeyRepository generatedKeyRepository;
    @Mock
    Environment environment;

    static MBClientType CLIENT_SPY;
    static MBRentPropertyType RENT_SPY;
    static MBRentContractType CONTRACT_SPY;

    @BeforeEach
    void setUp() {
        CLIENT.setCode("cl1");

        CONTRACT.setCode("Code");
        CONTRACT.setFrom(LocalDate.now());
        CONTRACT.setTo(LocalDate.now());
        CONTRACT.setClient(CLIENT);
        CONTRACT.setRentProperty(RENT);
        CONTRACT.setContractFileName("contractFileName");


        RENT.setCode("1000");
        RENT.setActive(true);
        RENT.setContracts(Set.of(CONTRACT));
        RENT.setCurrentContract(CONTRACT);
        RENT.setUnit(30);
        RENT.setCost(100);

        CLIENT_SPY = spy(CLIENT);
        RENT_SPY = spy(RENT);
        CONTRACT_SPY = spy(CONTRACT);
        RENT_SPY.setCurrentContract(CONTRACT_SPY);
    }

    @Test
    void updateContract() {
        when(typeService.findItemByCode(CONTRACT.getCode(), MBRentContractType.class)).thenReturn(CONTRACT_SPY);

        service.updateContract(CONTRACT);

        verify(CONTRACT_SPY).setFrom(CONTRACT.getFrom());
        verify(CONTRACT_SPY).setTo(CONTRACT.getTo());
        verify(CONTRACT_SPY).setUnit(RENT.getUnit());
        verify(CONTRACT_SPY).setCostPerUnit(RENT.getCost());
        verify(CONTRACT_SPY).setContractFileName(CONTRACT.getContractFileName());
        verify(typeService).save(CONTRACT_SPY);
    }

    @Test
    void createContract() {
        GeneratedKey key = new GeneratedKey();
        key.setGeneratedValue(23l);
        when(generatedKeyRepository.save(new GeneratedKey())).thenReturn(key);
        when(typeService.findItemByCode(RENT.getCode(), MBRentPropertyType.class)).thenReturn(RENT_SPY);
        when(clientService.getClientByCode(CLIENT.getCode())).thenReturn(CLIENT_SPY);
        when(environment.getProperty(CONTRACT_CODE_PREFIX, "CC-")).thenReturn("CC-");
        when(typeService.create(MBRentContractType.class)).thenReturn(CONTRACT_SPY);
        CONTRACT.setCode(null);

        service.updateContract(CONTRACT);
        verify(CONTRACT_SPY).setCode("CC-"+key.getGeneratedValue());
        verify(CONTRACT_SPY).setRentProperty(RENT_SPY);
        verify(CONTRACT_SPY).setClient(CLIENT_SPY);
        verify(CONTRACT_SPY).setFrom(CONTRACT.getFrom());
        verify(CONTRACT_SPY).setTo(CONTRACT.getTo());
        verify(CONTRACT_SPY).setUnit(RENT.getUnit());
        verify(CONTRACT_SPY).setCostPerUnit(RENT.getCost());
        verify(CONTRACT_SPY).setNextOrderDate(CONTRACT.getFrom().plusMonths(1));
        verify(CONTRACT_SPY).setContractFileName(CONTRACT.getContractFileName());
        verify(typeService).save(CONTRACT_SPY);
    }

    @Test
    void endContract() {
        when(typeService.findItemByCode(CONTRACT.getCode(), MBRentContractType.class)).thenReturn(CONTRACT_SPY);
        when(typeService.save(CONTRACT_SPY)).thenReturn(true);
        CONTRACT_SPY.setRentProperty(RENT_SPY);
        CONTRACT_SPY.setClient(CLIENT_SPY);


        service.endContract(CONTRACT.getCode(),END_OF_CONTRACT);
        verify(CONTRACT_SPY).setTo(END_OF_CONTRACT);
        verify(RENT_SPY).setCurrentContract(null);
        verify(typeService).save(CONTRACT_SPY);
    }
}