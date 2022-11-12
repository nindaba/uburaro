package bi.uburaro.initialdata.services.impl;

import bi.uburaro.core.services.TypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultImporterServiceTest {
    @Mock
    TypeService typeService;
    @Autowired
    DefaultImporterService importerService;

    @Test
    void importCurrent() {
        importerService.importCurrent();
    }

    @Test
    void importFromDir() {
        Integer i =0;
        adder(i);
        assertEquals(1,i);
    }
    void adder(Integer i){
        i++;
    }
}