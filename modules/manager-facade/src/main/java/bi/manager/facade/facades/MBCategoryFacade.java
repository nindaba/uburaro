package bi.manager.facade.facades;

import bi.manager.facade.data.MBCategoryData;

import java.util.Collection;
import java.util.Set;

public interface MBCategoryFacade {
    /**
     * Gets all the categories that belong to the facility {@code  facilityCode} which are active
     *
     * @param facilityCode
     * @param allFields
     * @return collection of categories
     */
    Collection<MBCategoryData> getCategoriesByFacilityCode(String facilityCode,boolean allFields );

    /**
     * Gets a category with {@code code}
     *
     * @param code
     * @param allFields
     * @return category
     */
    MBCategoryData getCategoryByCode(String code, boolean allFields);

    /**
     * Finds a category with {@code category.code()} if found it is updated,
     * if the category is not found it will be created
     *
     * @param category
     */
    void updateCategory(MBCategoryData category);

    /**
     * Deletes all the categories that have {@code  codes}
     *
     * @param codes
     */
    void deleteCategories(Set<String> codes);

}
