package bi.uburaro.core.services.impl;

import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.strategies.PrimaryKeyGeneratorStrategy;
import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.PrimaryKeyType;
import bi.uburaro.core.validators.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultTypeServiceTest {

    @InjectMocks
    DefaultTypeService typeService;
    @Mock
    PrimaryKeyGeneratorStrategy primaryKeyGeneratorStrategy;

    @Mock
    List<Validator<ItemType>> itemBeforeSaveValidators;
    @Mock
    ItemRepository itemRepository;

    @BeforeEach
    void setUp(){
    }

//    @Test
    void create(){
        when(primaryKeyGeneratorStrategy.generatePrimaryKey(HotelType.ITEM_TYPE)).thenReturn(new PrimaryKeyType());
        when(itemBeforeSaveValidators.stream()).thenReturn(Stream.empty());
        when(itemRepository.save(any())).thenReturn(any());
        HotelType actual = typeService.create(HotelType.class);

        assertNotNull(actual);
        verify(primaryKeyGeneratorStrategy,times(1)).generatePrimaryKey(HotelType.class.getName());
    }
}