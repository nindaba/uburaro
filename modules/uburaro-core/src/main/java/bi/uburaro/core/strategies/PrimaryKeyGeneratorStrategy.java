package bi.uburaro.core.strategies;

import bi.uburaro.core.types.PrimaryKeyType;

public interface PrimaryKeyGeneratorStrategy {
    PrimaryKeyType generatePrimaryKey(final String itemType);
}
