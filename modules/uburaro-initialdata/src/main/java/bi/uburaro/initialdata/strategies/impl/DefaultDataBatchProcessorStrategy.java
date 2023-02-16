package bi.uburaro.initialdata.strategies.impl;

import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.utils.MessageUtils;
import bi.uburaro.initialdata.data.BatchData;
import bi.uburaro.initialdata.data.BatchLineData;
import bi.uburaro.initialdata.mappers.Mapper;
import bi.uburaro.initialdata.strategies.DataBatchProcessorStrategy;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static bi.uburaro.initialdata.InitialdataConstants.*;

public class DefaultDataBatchProcessorStrategy implements DataBatchProcessorStrategy {

    protected final List<Mapper<ItemType>> mappers;
    protected final TypeService typeService;
    private static final Logger log = LogManager.getLogger();

    public DefaultDataBatchProcessorStrategy(List<Mapper<ItemType>> mappers, TypeService typeService) {
        this.mappers = mappers;
        this.typeService = typeService;
    }

    @Transactional
    @Override
    public boolean processBatch(final BatchData batchData) {
        if (!validateBatch(batchData) && CollectionUtils.isEmpty(batchData.getValues())) {
            return false;
        }

        final Integer startOfBatch = batchData.getFields().getLineNumber();

        log.info("File {} Validation for Batch from line:{} was success, starting to import values", batchData.getDataFile(), startOfBatch);

        return findSupportedMapper(batchData.getTarget())
                .map(mapper -> {
                    final Map<ItemType, BatchLineData> targetValues = batchData.getValues().stream()
                            .collect(Collectors.toMap(
                                    value -> insertUpdate(value.getValue(), mapper),
                                    value -> value));

                    mapper.set(batchData.getFields(), targetValues);
                    targetValues.keySet().forEach(typeService::save);

                    log.info("Imported batch from line:{} to {}", startOfBatch, getEndOfBatch(batchData).orElse(0));
                    batchData.getValues().removeIf(batchLineData -> !batchLineData.isFailed());
                    return true;
                })
                .orElseGet(() -> {
                    log.error("No mapper found for {}", batchData.getTarget());
                    batchData.getValues().clear();
                    return false;
                });
    }

    protected Optional<Mapper<ItemType>> findSupportedMapper(final String target) {
        return mappers.stream()
                .filter(mapper -> isClassMatch(mapper.getTargetClass(),target))
                .findFirst();
    }

    private boolean isClassMatch(final Class<ItemType> targetClass,final String target) {
        return StringUtils.equals(targetClass.getName(), MessageUtils.format(TYPE_MANAGER_CLASS, StringUtils.capitalize(target))) ||
                StringUtils.equals(targetClass.getName(), MessageUtils.format(TYPE_MANAGER_CLIENT_CLASS, StringUtils.capitalize(target))) ||
                StringUtils.equals(targetClass.getName(), MessageUtils.format(TYPE_UBURARO_CLASS, StringUtils.capitalize(target))) ||
                StringUtils.equals(targetClass.getName(), MessageUtils.format(TYPE_GROUPS_CLASS, StringUtils.capitalize(target)));

    }

    protected ItemType insertUpdate(final String value, Mapper<ItemType> mapper) {
        try {
            return typeService.findItemByCode(StringUtils.split(value, DELIMITER, 2)[0], mapper.getTargetClass());
        } catch (NotFoundException e) {
            return typeService.create(mapper.getTargetClass());
        }
    }

    protected Optional<Integer> getStartOfBatch(final BatchData batchData) {
        if (batchData.getFields() != null &&
                batchData.getFields().getLineNumber() != null) {
            return Optional.of(batchData.getFields().getLineNumber() - 1);
        }
        return Optional.empty();
    }

    protected Optional<Integer> getEndOfBatch(final BatchData batchData) {
        final List<BatchLineData> values = batchData.getValues();
        if (CollectionUtils.isNotEmpty(values)) {
            return Optional.ofNullable(values.get(values.size() - 1).getLineNumber());
        }
        return Optional.empty();
    }

    protected boolean validateBatch(final BatchData batchData) {
        final BatchLineData fields = batchData.getFields();

        if (fields != null) {
            log.info("Validating Fields in {}", batchData.getDataFile());
            if (!validateFileLine(fields) && StringUtils.isEmpty(batchData.getTarget())) {
                log.error("The Batch from line:{} has no target or the target is invalid", fields.getLineNumber() - 1);
                return false;
            }
        }
        return true;
    }

    protected boolean validateFileLine(final BatchLineData fileLineData) {
        if (fileLineData.getLineNumber() == null) {
            log.error("The BatchLine with no line number");
            return false;
        }
        if (StringUtils.isEmpty(fileLineData.getValue())) {
            log.error("The no values on line:{} ", fileLineData.getLineNumber());
            return false;
        }
        return true;
    }
}
