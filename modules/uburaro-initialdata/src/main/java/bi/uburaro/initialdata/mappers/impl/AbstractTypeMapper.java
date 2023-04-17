package bi.uburaro.initialdata.mappers.impl;

import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.importer.BatchLineType;
import bi.uburaro.initialdata.mappers.Mapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static bi.uburaro.initialdata.InitialdataConstants.DELIMITER;

public abstract class AbstractTypeMapper<TYPE extends ItemType> implements Mapper<TYPE> {
    private final Logger log = LogManager.getLogger();
    protected final TypeService typeService;

    protected AbstractTypeMapper(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public void set(final BatchLineType fields, final BatchLineType line, final TYPE target) {
        if (StringUtils.isNotBlank(line.getValue())) {
            final List<String> keys = List.of(StringUtils.split(fields.getValue(), DELIMITER));
            final List<String> values = new ArrayList<>(List.of(line.getValue().split(DELIMITER)));

            IntStream.range(0, keys.size() - values.size())
                    .forEach(index -> values.add(null));

            IntStream.range(0, keys.size())
                    .collect(HashMap<String, String>::new,
                            (map, index) -> map.put(keys.get(index), values.get(index)),
                            Map::putAll)
                    .forEach((key, value) -> setTargetValue(
                            line,
                            createFieldsMapper(target).getOrDefault(key, noSuchField(key, target.getClass().getName())),
                            value));

            typeService.delete(line);
        }
    }

    private void setTargetValue(BatchLineType line, Consumer<String> mapper, String value) {
        try {
            if (StringUtils.isNoneEmpty(value)) {
                mapper.accept(value);
            }
        } catch (NotFoundException e) {
            line.setFailed(true);
            log.error("{} on Line:{} ", e.getMessage(), line.getLineNumber());
        }
    }


    @Override
    public void set(final BatchLineType fields, final Map<TYPE, BatchLineType> targetValues) {
        targetValues.forEach((target, values) -> set(fields, values, target));
    }


    protected Stream<String> getStringStream(String values) {
        return Arrays.stream(StringUtils.split(values, DELIMITER));
    }

    protected Consumer<String> noSuchField(String field, String itemType) {
        return value -> log.warn("No such field {} on {} ", field, itemType);
    }
}
