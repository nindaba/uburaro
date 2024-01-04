package bi.uburaro.core.mergers.impl;

import bi.uburaro.core.mergers.Merger;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.validators.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Log4j2
public class RepositoriesListMerger implements Merger<List<ItemRepository>,ItemRepository> {
    private List<ItemRepository> repositories = new ArrayList<>();
    final private List<ItemRepository> target;

    public RepositoriesListMerger(List<ItemRepository> target) {
        this.target = target;
    }

    @Override
    public void setAdd(ItemRepository item) {
        repositories.add(item);
    }

    @Override
    public void setAdd(Collection<ItemRepository> itemRepositories) {
        repositories.addAll(itemRepositories);
    }

    @Override
    public void setRemove(ItemRepository item) {
        repositories.remove(item);
    }

    @Override
    public void merge() {
        if(!CollectionUtils.isEmpty(repositories)){
            log.info("Merging {} Repository beans ", repositories::size);
            target.addAll(repositories);
        }
    }
}
