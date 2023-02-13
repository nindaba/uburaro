package bi.manager.web.controllers;

import bi.manager.facade.data.MBCapitalData;
import bi.manager.facade.data.MBCapitalEntryData;
import bi.manager.facade.data.MBCategoryData;
import bi.manager.facade.facades.MBCapitalFacade;
import bi.manager.facade.facades.MBCategoryFacade;
import bi.manager.web.ManagerWebConstants;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping(value = ManagerWebConstants.Controller.Category.endpoint)
@CrossOrigin()
public class CategoryController {
    @Resource(name = "mbCategoryFacade")
    protected MBCategoryFacade facade;

    @GetMapping
    public Collection<MBCategoryData> getCategories(@PathVariable String code, @RequestParam(required = false) boolean allFields) {
        return facade.getCategoriesByFacilityCode(code, allFields);
    }

    @GetMapping(value = "/{categoryCode}")
    public MBCategoryData getCategory(@PathVariable String categoryCode, @RequestParam(required = false) boolean allFields) {
        return facade.getCategoryByCode(categoryCode, allFields);
    }

    @DeleteMapping
    public void deleteCategories(@RequestParam Set<String> codes) {
        facade.deleteCategories(codes);
    }
    @PatchMapping
    public void updateCategory(@RequestBody MBCategoryData category){
        facade.updateCategory(category);
    }
}