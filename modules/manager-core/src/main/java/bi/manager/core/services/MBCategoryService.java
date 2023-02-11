package bi.manager.core.services;

import bi.manager.core.types.MBCategoryType;

import java.util.Collection;
import java.util.Set;

public interface MBCategoryService {
    /**
     * Gets all the categories that belong to the facility {@code  facilityCode} which are active
     *
     * @param facilityCode
     * @return collection of categories
     */
    Collection<MBCategoryType> getCategoriesByFacilityCode(String facilityCode);

    /**
     * Gets a category with {@code code}
     *
     * @param code
     * @return category
     */
    MBCategoryType getCategoryByCode(String code);

    /**
     * Finds a category with {@code category.code()} if found it is updated,
     * if the category is not found it will be created
     *
     * @param category
     */
    void updateCategory(MBCategoryType category);

    /**
     * Deletes all the categories that have {@code  codes}
     *
     * @param codes
     */
    void deleteCategories(Set<String> codes);

}
