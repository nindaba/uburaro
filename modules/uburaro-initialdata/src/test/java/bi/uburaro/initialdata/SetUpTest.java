package bi.uburaro.initialdata;

import bi.uburaro.core.services.SessionService;
import bi.uburaro.core.services.impl.DefaultTypeService;
import bi.uburaro.core.strategies.PrimaryKeyGeneratorStrategy;
import bi.uburaro.core.types.AddressType;
import bi.uburaro.core.types.CustomerType;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.PrimaryKeyType;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.function.Consumer;

import static bi.uburaro.core.types.CustomerType.*;
import static bi.uburaro.core.types.ItemType.CODE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SetUpTest {
    @InjectMocks
    DefaultTypeService defaultTypeService;
    @Mock
    PrimaryKeyGeneratorStrategy primaryKeyGeneratorStrategy;
    @Mock
    SessionService sessionService;
    @Test
    void testInsert() throws ClassNotFoundException {
        String type = "customer";
//        CustomerType customer = defaultTypeService.create(CustomerType.class);
//
//        when(primaryKeyGeneratorStrategy.generateKey(CustomerType.ITEM_TYPE)).thenReturn(new PrimaryKeyType());
//        doReturn(new AddressType()).when(defaultTypeService.findItemByCode("add", AddressType.class));

//        assertEquals(customer.getCode(), "reading");

    }

    private void logNoField(String code, String type) {
        System.out.printf("%s is not an attribute of %s type\n", code, type);
    }
}
