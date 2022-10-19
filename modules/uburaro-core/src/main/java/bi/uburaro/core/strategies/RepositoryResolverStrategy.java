package bi.uburaro.core.strategies;

import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;

public interface RepositoryResolverStrategy {
    <TYPE extends ItemType> ItemRepository resolveRepository(Class<TYPE> typeClass);
}
