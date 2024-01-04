package bi.uburaro.core.strategies.impl;

import bi.uburaro.core.repositories.GeneratedKeyRepository;
import bi.uburaro.core.types.PrimaryKeyType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPrimaryKeyGeneratorStrategyTest {
    @Mock
    GeneratedKeyRepository generatedKeyRepository;

    @Test
    void generateKey() {
        DefaultPrimaryKeyGeneratorStrategy strategy = new DefaultPrimaryKeyGeneratorStrategy(generatedKeyRepository);
        PrimaryKeyType actual = strategy.generatePrimaryKey(PrimaryKeyType.PRIMARY_KEY);
        assertNotNull(actual);
        assertEquals(PrimaryKeyType.PRIMARY_KEY,actual.getItemType());
        assertTrue(actual.getDateCreated() > 0 && actual.getDateCreated() < System.currentTimeMillis());
    }
}
