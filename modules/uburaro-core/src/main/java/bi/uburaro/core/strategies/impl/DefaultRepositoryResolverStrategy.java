package bi.uburaro.core.strategies.impl;

import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.strategies.RepositoryResolverStrategy;
import bi.uburaro.core.types.ItemType;

import java.util.List;

public class DefaultRepositoryResolverStrategy implements RepositoryResolverStrategy {
    private final List<ItemRepository> repositories;
    private final ItemRepository itemRepository;

    public DefaultRepositoryResolverStrategy(List<ItemRepository> repositories, ItemRepository itemRepository) {
        this.repositories = repositories;
        this.itemRepository = itemRepository;
    }


    @Override
    public <TYPE extends ItemType> ItemRepository resolveRepository(Class<TYPE> typeClass) {
        return repositories.stream()
                .filter(itemRepository -> itemRepository.belongsTo(typeClass))
                .findAny()
                .orElse(itemRepository);
    }
}
