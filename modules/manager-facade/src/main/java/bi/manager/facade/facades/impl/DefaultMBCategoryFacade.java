package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBCategoryService;
import bi.manager.core.types.MBCategoryType;
import bi.manager.facade.converters.category.CategoryMapper;
import bi.manager.facade.converters.category.FullCategoryMapper;
import bi.manager.facade.data.MBCategoryData;
import bi.manager.facade.facades.MBCategoryFacade;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service(value = "mbCategoryFacade")
public class DefaultMBCategoryFacade implements MBCategoryFacade {

    protected final MBCategoryService service;
    protected final CategoryMapper mapper;
    protected final FullCategoryMapper fullMapper;

    public DefaultMBCategoryFacade(MBCategoryService service, CategoryMapper mapper, FullCategoryMapper fullMapper) {
        this.service = service;
        this.mapper = mapper;
        this.fullMapper = fullMapper;
    }

    @Override
    public Collection<MBCategoryData> getCategoriesByFacilityCode(String facilityCode, boolean allFields) {
        Collection<MBCategoryType> categories = service.getCategoriesByFacilityCode(facilityCode);
        return allFields ? fullMapper.categoriesToData(categories) : mapper.categoriesToData(categories);
    }

    @Override
    public MBCategoryData getCategoryByCode(String code, boolean allFields) {
        MBCategoryType category = service.getCategoryByCode(code);
        if (category != null) {
            return allFields ? fullMapper.categoryToData(category) : mapper.categoryToData(category);
        }
        return null;
    }

    @Override
    public void updateCategory(MBCategoryData category) {
        service.updateCategory(mapper.categoryToType(category));
    }

    @Override
    public void deleteCategories(Set<String> codes) {
        service.deleteCategories(codes);
    }
}
