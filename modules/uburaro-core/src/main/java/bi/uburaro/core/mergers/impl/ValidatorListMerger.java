package bi.uburaro.core.mergers.impl;

import bi.uburaro.core.mergers.Merger;
import bi.uburaro.core.validators.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
@Log4j2
public class ValidatorListMerger implements Merger<List<Validator>,Validator> {
    private List<Validator> validators = new ArrayList<>();
    final private List<Validator> target;

    public ValidatorListMerger(List<Validator> target) {
        this.target = target;
    }

    @Override
    public void setAdd(Validator validator) {
        validators.add(validator);
    }

    @Override
    public void setRemove(Validator validator) {
        validators.remove(validator);
    }

    @Override
    public void merge() {
        if(!CollectionUtils.isEmpty(validators)){
            log.info("Merging {} Validator beans ",validators::size);
            target.addAll(validators);
        }
    }
}
