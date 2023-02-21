package bi.manager.core.services.impl;

import bi.manager.core.services.MBCategoryService;
import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBFacilityType;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "mBCategoryService")
public class DefaultMBCategoryService extends AbstractMBTypeService implements MBCategoryService {

    public DefaultMBCategoryService(TypeService typeService) {
        super(typeService);
    }

    @Override
    public Collection<MBCategoryType> getCategoriesByFacilityCode(String facilityCode) {
        MBFacilityType facility = typeService.findItemByCode(facilityCode, MBFacilityType.class);
        if (facility != null) {
            return facility.getCategories().stream()
                    .filter(ItemType::isActive)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public MBCategoryType getCategoryByCode(String code) {
        return typeService.findItemByCode(code, MBCategoryType.class);
    }

    @Override
    public void updateCategory(final MBCategoryType category) {
        MBCategoryType categoryByCode;
        try {
            categoryByCode = typeService.findItemByCode(category.getCode(), MBCategoryType.class);
        } catch (NotFoundException e) {
            categoryByCode = typeService.create(MBCategoryType.class);
            categoryByCode.setCode(category.getCode());
        }
        categoryByCode.setActive(true);
        populateCategory(categoryByCode, category);
        typeService.save(categoryByCode);
    }

    private void populateCategory(MBCategoryType target, MBCategoryType source) {
        MBFacilityType itemByCode = validateAndGetFacility(source.getFacility());
        target.setFacility(itemByCode);

        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }
    }

    @Override
    public void deleteCategories(Set<String> codes) {
        codes.stream()
                .map(this::getCategoryByCodeWithCatch)
                .filter(Objects::nonNull)
                .peek(facility -> facility.setActive(false))
                .forEach(typeService::save);
    }

    protected ItemType getCategoryByCodeWithCatch(String s) {
        try {
            return getCategoryByCode(s);
        } catch (NotFoundException e) {
            return null;
        }
    }

    @Override
    public ItemType getItemByCodeWithTry(String code) {
        return getCategoryByCode(code);
    }
}
