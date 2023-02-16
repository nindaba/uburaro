package bi.uburaro.core.services.impl;

import bi.uburaro.core.UburaroCoreConstants;
import bi.uburaro.core.exceptions.ItemCreationException;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.exceptions.ValidationException;
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

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
        try {
            String type = (String) typeClass.getField(UburaroCoreConstants.ITEM_TYPE).get(null);
            TYPE instance = typeClass.getConstructor().newInstance();
            instance.setPrimaryKey(primaryKeyGeneratorStrategy.generateKey(type));

            Date dateModified = new Date();
            instance.setDateModified(dateModified);
            ModificationLogType logType = new ModificationLogType();
            String modificationKey = (String) ModificationLogType.class.getField(UburaroCoreConstants.ITEM_TYPE).get(null);
            logType.setPrimaryKey(primaryKeyGeneratorStrategy.generateKey(modificationKey));
            logType.setModifiedItem(type);
            logType.setDateModified(dateModified);

            if (sessionService != null && sessionService.getCurrentUser() != null) {
                logType.setUser(sessionService.getCurrentUser());
            }

//            if(logType.getUser() == null){
//                logType.setUser(sessionService.getAnonymousUser());
//            }
            save(logType);
            instance.setModificationLogs(Set.of(logType));

            return instance;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchFieldException |
                 NoSuchMethodException e) {
            log.debug(ERROR_RESPONSE, typeClass::getName);
            throw new ItemCreationException(e, format(ERROR_RESPONSE, typeClass::getName));
        }
    }

    @Override
    public <TYPE extends ItemType> boolean save(TYPE item) {
        Map<String, String> errors = new HashMap<>();
        itemBeforeSaveValidators.stream()
                .filter(validator -> validator.isSupported(item.getClass()))
                .forEach(validator -> validator.validate(item, errors));

        if (!errors.isEmpty()) {
            StringBuilder errorBuilder = new StringBuilder();
            errors.forEach((field, error) -> {
                errorBuilder.append(MessageUtils.format(error, field));
                log.error(error, field);
            });
            throw new ValidationException(errorBuilder.toString());
        }

        return itemRepository.save(item) != null;
    }

    //    @Transactional
    @Override
    public <TYPE extends ItemType> TYPE findItemByCode(String code, Class<TYPE> itemTypeClass) {
        try {
            return Optional.ofNullable(repositoryResolverStrategy.resolveRepository(itemTypeClass)
                            .findByCodeAndPrimaryKeyItemType(
                                    code,
                                    (String) itemTypeClass.getField(UburaroCoreConstants.ITEM_TYPE).get(null)))
                    .map(itemTypeClass::cast)
                    .orElseThrow(() -> {
                        log.debug(NOT_FOUND_RESPONSE, itemTypeClass::getSimpleName, () -> code);
                        return new NotFoundException(MessageUtils.format(
                                NOT_FOUND_RESPONSE, itemTypeClass::getSimpleName, () -> code));
                    });
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    //    @Transactional
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

    @Override
    public <TYPE extends ItemType> void delete(TYPE item) {
        repositoryResolverStrategy.resolveRepository(item.getClass()).delete(item);
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
