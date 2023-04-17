package bi.uburaro.initialdata.strategies.impl;

import bi.manager.core.repositories.MBInventoryOrderRepository;
import bi.manager.core.services.MBInventoryOrderService;
import bi.manager.core.types.MBInventoryOrderType;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.importer.BatchLineType;
import bi.uburaro.core.types.importer.BatchType;
import bi.uburaro.core.utils.MessageUtils;
import bi.uburaro.initialdata.mappers.Mapper;
import bi.uburaro.initialdata.strategies.DataBatchProcessorStrategy;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static bi.uburaro.initialdata.InitialdataConstants.*;

public class DefaultDataBatchProcessorStrategy implements DataBatchProcessorStrategy {
    protected final MBInventoryOrderService inventoryOrderService;
    protected final List<Mapper<ItemType>> mappers;
    protected final TypeService typeService;
    private static final Logger log = LogManager.getLogger();

    public DefaultDataBatchProcessorStrategy(MBInventoryOrderService inventoryOrderService, List<Mapper<ItemType>> mappers, TypeService typeService) {
        this.inventoryOrderService = inventoryOrderService;
        this.mappers = mappers;
        this.typeService = typeService;
    }

    @Transactional
    @Override
    public boolean processBatch(final BatchType batchData) {
        if (!validateBatch(batchData) && CollectionUtils.isEmpty(batchData.getValues())) {
            return false;
        }

        final Integer startOfBatch = batchData.getFields().getLineNumber();

        log.info("File {} Validation for Batch from line:{} was success, starting to import values", batchData.getDataFile(), startOfBatch);

        return findSupportedMapper(batchData.getTarget())
                .map(mapper -> {
                    final Map<ItemType, BatchLineType> targetValues = batchData.getValues().stream()
                            .peek(value -> value.setFailed(false))
                            .collect(Collectors.toMap(
                                    value -> insertUpdate(value.getValue(), mapper),
                                    value -> value));

                    mapper.set(batchData.getFields(), targetValues);
                    targetValues.forEach(this::save);
                    batchData.getValues().removeIf(data -> !data.isFailed());

                    log.info("Imported batch from line:{} to {}", startOfBatch, getEndOfBatch(batchData).orElse(0));
                    return true;
                })
                .orElseGet(() -> {
                    log.error("No mapper found for {}", batchData.getTarget());
                    batchData.getValues().clear();
                    return false;
                });
    }

    private void save(ItemType itemType, BatchLineType batchLineType) {
        if (!batchLineType.isFailed()) {
            if (itemType instanceof MBInventoryOrderType) {
                try {
                    inventoryOrderService.placeOrder((MBInventoryOrderType) itemType);
                    typeService.delete(itemType);
                } catch (NotFoundException ex) {
                    log.error(ex);
                    batchLineType.setFailed(true);
                }
            } else {
                typeService.save(itemType);
            }
        }
    }

    protected Optional<Mapper<ItemType>> findSupportedMapper(final String target) {
        return mappers.stream()
                .filter(mapper -> isClassMatch(mapper.getTargetClass(), target))
                .findFirst();
    }

    private boolean isClassMatch(final Class<ItemType> targetClass, final String target) {
        return StringUtils.equals(targetClass.getName(), MessageUtils.format(TYPE_MANAGER_CLASS, StringUtils.capitalize(target))) ||
                StringUtils.equals(targetClass.getName(), MessageUtils.format(TYPE_MANAGER_CLIENT_CLASS, StringUtils.capitalize(target))) ||
                StringUtils.equals(targetClass.getName(), MessageUtils.format(TYPE_UBURARO_CLASS, StringUtils.capitalize(target))) ||
                StringUtils.equals(targetClass.getName(), MessageUtils.format(TYPE_GROUPS_CLASS, StringUtils.capitalize(target)));

    }

    protected ItemType insertUpdate(final String value, Mapper<ItemType> mapper) {
        if (mapper.getTargetClass().equals(MBInventoryOrderType.class)) {
            return typeService.create(mapper.getTargetClass());
        }
        try {
            return typeService.findItemByCode(StringUtils.split(value, DELIMITER, 2)[0], mapper.getTargetClass());
        } catch (NotFoundException e) {
            return typeService.create(mapper.getTargetClass());
        }
    }

    protected Optional<Integer> getStartOfBatch(final BatchType batchData) {
        if (batchData.getFields() != null &&
                batchData.getFields().getLineNumber() != null) {
            return Optional.of(batchData.getFields().getLineNumber() - 1);
        }
        return Optional.empty();
    }

    protected Optional<Integer> getEndOfBatch(final BatchType batchData) {
        final List<BatchLineType> values = batchData.getValues();
        if (CollectionUtils.isNotEmpty(values)) {
            return Optional.ofNullable(values.get(values.size() - 1).getLineNumber());
        }
        return Optional.empty();
    }

    protected boolean validateBatch(final BatchType batchData) {
        final BatchLineType fields = batchData.getFields();

        if (fields != null) {
            log.info("Validating Fields in {}", batchData.getDataFile());
            if (!validateFileLine(fields) && StringUtils.isEmpty(batchData.getTarget())) {
                log.error("The Batch from line:{} has no target or the target is invalid", fields.getLineNumber() - 1);
                return false;
            }
        }
        return true;
    }

    protected boolean validateFileLine(final BatchLineType fileLineData) {
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
