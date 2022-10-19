package bi.uburaro.core.strategies.impl;

import bi.uburaro.core.types.PrimaryKeyType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPrimaryKeyGeneratorStrategyTest {

    @Test
    void generateKey() {
        DefaultPrimaryKeyGeneratorStrategy strategy = new DefaultPrimaryKeyGeneratorStrategy();
        PrimaryKeyType actual = strategy.generateKey(PrimaryKeyType.PRIMARY_KEY);
        assertNotNull(actual);
        assertEquals(PrimaryKeyType.PRIMARY_KEY,actual.getItemType());
        assertTrue(actual.getDateCreated() > 0 && actual.getDateCreated() < System.currentTimeMillis());
    }
}