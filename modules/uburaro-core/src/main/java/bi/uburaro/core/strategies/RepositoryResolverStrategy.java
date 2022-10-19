package bi.uburaro.core.strategies;

import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.types.ItemType;

public interface RepositoryResolverStrategy {
    /**
     * Iterates through all the repositories in the core and returns the repository related with the type
     *
     * @param typeClass
     * @return Repository extending ItemRepository
     * @param <TYPE>
     */
    <TYPE extends ItemType> ItemRepository resolveRepository(Class<TYPE> typeClass);
}
