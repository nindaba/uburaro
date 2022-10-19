package bi.uburaro.core.strategies.impl;

import bi.uburaro.core.strategies.PrimaryKeyGeneratorStrategy;
import bi.uburaro.core.types.PrimaryKeyType;


public class  DefaultPrimaryKeyGeneratorStrategy implements PrimaryKeyGeneratorStrategy {

    @Override
    public PrimaryKeyType generateKey(final String itemType) {
        PrimaryKeyType primaryKeyType = new PrimaryKeyType();
        primaryKeyType.setItemType(itemType);
        primaryKeyType.setDateCreated(System.currentTimeMillis());
        return primaryKeyType;
    }
}
