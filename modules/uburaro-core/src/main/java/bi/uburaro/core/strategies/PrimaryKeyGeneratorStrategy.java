package bi.uburaro.core.strategies;

import bi.uburaro.core.types.PrimaryKeyType;

public interface PrimaryKeyGeneratorStrategy {
    PrimaryKeyType generateKey(final String itemType);

}
