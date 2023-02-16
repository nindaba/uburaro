package bi.manager.core.services.impl;

import bi.manager.core.services.MBInventoryService;
import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBInventoryType;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "mBInventoryService")
public class DefaultMBInventoryService implements MBInventoryService {
    protected final TypeService typeService;

    public DefaultMBInventoryService(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public Collection<MBInventoryType> getInventoriesByCategory(final String categoryCode) {
        return typeService.findItemByCode(categoryCode, MBCategoryType.class).getInventories().stream()
                .filter(MBInventoryType::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public MBInventoryType getInventoryByCode(final String code) {
        return typeService.findItemByCode(code, MBInventoryType.class);
    }

    @Override
    public void updateInventory(final MBInventoryType inventory) {
        if (inventory != null && StringUtils.isNotEmpty(inventory.getCode())) {
            MBInventoryType itemByCode;
            try {
                itemByCode = typeService.findItemByCode(inventory.getCode(), MBInventoryType.class);
            } catch (NotFoundException e) {
                itemByCode = typeService.create(MBInventoryType.class);
                itemByCode.setCode(inventory.getCode());
            }
            itemByCode.setActive(true);
            populateInventory(inventory, itemByCode);
            populateCategory(inventory, itemByCode);
            typeService.save(itemByCode);
        }
    }

    private void populateCategory(final MBInventoryType source,final MBInventoryType target) {
        final MBCategoryType category = source.getCategory();

        if (category != null && StringUtils.isNotEmpty(category.getCode())) {
            final MBCategoryType categoryByCode = typeService.findItemByCode(category.getCode(), MBCategoryType.class);
            target.setCategory(categoryByCode);
            target.setActive(true);
        }
    }

    private void populateInventory(final MBInventoryType source,final MBInventoryType target) {
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }
    }

    @Override
    public void deleteInventory(final Set<String> codes) {
        codes.stream()
                .map(this::getInventoryByCodeWithTry)
                .filter(Objects::nonNull)
                .peek(mbInventoryType -> mbInventoryType.setActive(false))
                .forEach(typeService::save);
    }

    protected ItemType getInventoryByCodeWithTry(final String code) {
        try {
            return getInventoryByCode(code);
        } catch (Exception e) {
            return null;
        }
    }

}
