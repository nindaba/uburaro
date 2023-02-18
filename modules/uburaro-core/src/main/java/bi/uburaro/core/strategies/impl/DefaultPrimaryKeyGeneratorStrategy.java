package bi.uburaro.core.strategies.impl;

import bi.uburaro.core.repositories.GeneratedKeyRepository;
import bi.uburaro.core.strategies.PrimaryKeyGeneratorStrategy;
import bi.uburaro.core.types.GeneratedKey;
import bi.uburaro.core.types.PrimaryKeyType;


public class  DefaultPrimaryKeyGeneratorStrategy implements PrimaryKeyGeneratorStrategy {

    protected final GeneratedKeyRepository generatedKeyRepository;

    public DefaultPrimaryKeyGeneratorStrategy(GeneratedKeyRepository generatedKeyRepository) {
        this.generatedKeyRepository = generatedKeyRepository;
    }

    @Override
    public PrimaryKeyType generatePrimaryKey(final String itemType) {
        PrimaryKeyType primaryKeyType = new PrimaryKeyType();

        GeneratedKey generatedKey = new GeneratedKey();
        generatedKeyRepository.save(generatedKey);

        primaryKeyType.setKey(generatedKey);
        primaryKeyType.setItemType(itemType);
        primaryKeyType.setDateCreated(System.currentTimeMillis());
        return primaryKeyType;
    }

}
