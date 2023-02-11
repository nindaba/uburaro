package bi.manager.core.services.impl;

import bi.manager.core.services.MBCategoryService;
import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBFacilityType;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "mBCategoryService")
public class DefaultMBCategoryService implements MBCategoryService {

    protected final TypeService typeService;

    public DefaultMBCategoryService(TypeService typeService) {
        this.typeService = typeService;
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
    public void updateCategory(MBCategoryType category) {
        MBCategoryType oldCategory = typeService.findItemByCode(category.getCode(), MBCategoryType.class);

        if (oldCategory == null) {
            oldCategory = typeService.create(MBCategoryType.class);
            oldCategory.setCode(category.getCode());
        }
        populateCategory(oldCategory, category);
        typeService.save(oldCategory);
    }

    private void populateCategory(MBCategoryType target, MBCategoryType source) {
        target.setName(source.getName());
    }

    @Override
    public void deleteCategories(Set<String> codes) {
        codes.forEach(code -> typeService.delete(code, MBCategoryType.class));
    }
}
