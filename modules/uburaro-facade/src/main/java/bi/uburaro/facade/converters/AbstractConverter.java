package bi.uburaro.facade.converters;

import bi.uburaro.core.exceptions.ItemCreationException;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.facade.pupulators.Populator;
import bi.uburaro.facade.data.ItemData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static bi.uburaro.core.utils.MessageUtils.format;

public class AbstractConverter<SOURCE extends ItemType, TARGET extends ItemData> implements Converter<SOURCE, TARGET> {
    private static final String ERROR_RESPONSE = "Unable to create an instance of {}";
    private Class<TARGET> targetClass;
    private List<Populator<SOURCE, TARGET>> populators;
    private final Logger log = LogManager.getLogger();


    @Override
    public TARGET convert(final SOURCE source) {
        Assert.notNull(source, source.getClass().getName()+" Can not be null");
        try {
            TARGET target = targetClass.getConstructor().newInstance();
            populators.forEach(populator -> populator.populate(source, target));
            return target;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            log.debug(ERROR_RESPONSE, targetClass::getName);
            throw new ItemCreationException(e, format(ERROR_RESPONSE, targetClass::getName));
        }
    }

    public void setTargetClass(Class<TARGET> targetClass) {
        this.targetClass = targetClass;
    }

    public void setPopulators(List<Populator<SOURCE, TARGET>> populators) {
        this.populators = populators;
    }
}
