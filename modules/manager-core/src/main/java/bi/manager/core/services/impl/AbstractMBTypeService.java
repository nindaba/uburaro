package bi.manager.core.services.impl;

import bi.manager.core.services.MBTypeService;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;

import java.util.Objects;
import java.util.Set;

public abstract class AbstractMBTypeService<ITEM extends ItemType> implements MBTypeService<ITEM> {
    protected final TypeService typeService;

    protected AbstractMBTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public void deleteMBItem(Set<String> codes) {
        codes.stream()
                .map(this::getItemByCodeWithTry)
                .filter(Objects::nonNull)
                .peek(itemType -> itemType.setActive(false))
                .forEach(typeService::save);
    }
}
