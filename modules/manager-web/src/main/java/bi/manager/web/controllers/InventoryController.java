package bi.manager.web.controllers;

import bi.manager.facade.data.MBInventoryData;
import bi.manager.facade.facades.MBInventoryFacade;
import bi.manager.web.ManagerWebConstants;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

import static bi.manager.web.ManagerWebConstants.Controller.Inventory.inventory;

@RestController
@CrossOrigin()
public class InventoryController {
    @Resource(name = "mBInventoryFacade")
    protected MBInventoryFacade facade;

    @GetMapping(value = ManagerWebConstants.Controller.Inventory.endpoint)
    public Collection<MBInventoryData> getInventories(@PathVariable String code, @RequestParam(required = false) boolean allFields) {
        return facade.getInventoryByFacilityCode(code);
    }

    @GetMapping(value = ManagerWebConstants.Controller.Inventory.categoryInventories)
    public Collection<MBInventoryData> getCategoryInventories(@PathVariable String categoryCode, @RequestParam(required = false) boolean allFields) {
        return facade.getInventoriesByCategory(categoryCode, allFields);
    }

    @GetMapping(value = inventory )
    public MBInventoryData getInventory(@RequestParam(required = false) boolean allFields, @PathVariable String code, @PathVariable String inventoryCode){
        return facade.getInventoryByCode(inventoryCode,allFields);
    }

    @DeleteMapping(value = ManagerWebConstants.Controller.Inventory.endpoint)
    public void deleteCategories(@RequestParam Set<String> codes) {
        facade.deleteInventory(codes);
    }
    @PatchMapping(value = inventory)
    public void updateCategory(@RequestBody MBInventoryData category){
        facade.updateInventory(category);
    }
}
