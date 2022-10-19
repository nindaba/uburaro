package bi.uburaro.core.services.impl;

import bi.uburaro.core.UburaroCoreConstants;
import bi.uburaro.core.exceptions.ItemCreationException;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.repositories.ItemRepository;
import bi.uburaro.core.services.SessionService;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.strategies.PrimaryKeyGeneratorStrategy;
import bi.uburaro.core.strategies.RepositoryResolverStrategy;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.ModificationLogType;
import bi.uburaro.core.utils.MessageUtils;
import bi.uburaro.core.validators.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static bi.uburaro.core.utils.MessageUtils.format;

@Log4j2
public class DefaultTypeService implements TypeService {
    private static final String ERROR_RESPONSE = "Unable to create an instance of {}";
    private static final String NOT_FOUND_RESPONSE = "Unable to find {} of code {}";
    private final PrimaryKeyGeneratorStrategy primaryKeyGeneratorStrategy;
    private final RepositoryResolverStrategy repositoryResolverStrategy;
    private final ItemRepository itemRepository;
    private final List<Validator<ItemType>> itemBeforeSaveValidators;
    private final SessionService sessionService;

    public DefaultTypeService(PrimaryKeyGeneratorStrategy primaryKeyGeneratorStrategy,
                              RepositoryResolverStrategy repositoryResolverStrategy,
                              ItemRepository itemRepository,
                              List<Validator<ItemType>> itemBeforeSaveValidators, SessionService sessionService) {
        this.primaryKeyGeneratorStrategy = primaryKeyGeneratorStrategy;
        this.repositoryResolverStrategy = repositoryResolverStrategy;
        this.itemRepository = itemRepository;
        this.itemBeforeSaveValidators = itemBeforeSaveValidators;
        this.sessionService = sessionService;
    }

    @Override
    public <TYPE extends ItemType> TYPE create(final Class<TYPE> typeClass) {
        TYPE instance;
        try {
            String type = (String) typeClass.getField(UburaroCoreConstants.ITEM_TYPE).get(null);
            instance = typeClass.getConstructor().newInstance();
            instance.setPrimaryKey(primaryKeyGeneratorStrategy.generateKey(type));

            Date dateModified = new Date();
            instance.setDateModified(dateModified);
            ModificationLogType logType = new ModificationLogType();
            logType.setModifiedItem(type);
            logType.setDateModified(dateModified);

            if (sessionService != null && sessionService.getCurrentUser() != null) {
                logType.setUser(sessionService.getCurrentUser());
            }
            instance.setModificationLogs(List.of(logType));

            return instance;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchFieldException |
                 NoSuchMethodException e) {
            log.debug(ERROR_RESPONSE, typeClass::getName);
            throw new ItemCreationException(e, format(ERROR_RESPONSE, typeClass::getName));
        }
    }

    @Override
    public <TYPE extends ItemType> void save(TYPE item) {
        Map<String, String> errors = new HashMap<>();
        itemBeforeSaveValidators.stream()
                .filter(validator -> validator.isSupported(item.getClass()))
                .forEach(validator -> validator.validate(item, errors));

        if (errors.isEmpty()) {
            itemRepository.save(item);
        } else errors.forEach((field, error) -> log.error(error, field));
    }

    @Transactional
    @Override
    public <TYPE extends ItemType> TYPE findItemByCode(String code, Class<TYPE> itemTypeClass) {
        ItemType itemType = repositoryResolverStrategy.resolveRepository(itemTypeClass)
                .findByCodeAndPrimaryKeyItemType(code, itemTypeClass.getName());

        if (ObjectUtils.isEmpty(itemType)) {
            log.debug(NOT_FOUND_RESPONSE, itemTypeClass::getSimpleName, () -> code);
            throw new NotFoundException(
                    MessageUtils.format(NOT_FOUND_RESPONSE, itemTypeClass::getSimpleName, () -> code));
        }

        return itemTypeClass.cast(itemType);
    }

    @Override
    public <TYPE extends ItemType> List<TYPE> findAll(Class<TYPE> typeClass) {
        return repositoryResolverStrategy.resolveRepository(typeClass)
                .findAll();
    }

    @Override
    public <TYPE extends ItemType> void delete(String code, Class<TYPE> typeClass) {
        repositoryResolverStrategy.resolveRepository(typeClass)
                .deleteByCodeAndPrimaryKeyItemType(code, typeClass.getName());
    }

    /**
     * Creates a new Instance of modification log and set up the dates and user and the type;
     *
     * @param type of the item modified
     * @return Modification log
     */
    public ModificationLogType createModificationLog(String type) {

        ModificationLogType logType = new ModificationLogType();
        logType.setPrimaryKey(primaryKeyGeneratorStrategy.generateKey(ModificationLogType.ITEM_TYPE));

        logType.setModifiedItem(type);
        logType.setDateModified(new Date());
        if (sessionService != null && sessionService.getCurrentUser() != null) {
            logType.setUser(sessionService.getCurrentUser());
        }
        return logType;
    }
}
