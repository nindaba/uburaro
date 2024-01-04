package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBInventoryService;
import bi.manager.core.types.MBInventoryType;
import bi.manager.facade.converters.inventory.FullInventoryMapper;
import bi.manager.facade.converters.inventory.InventoryMapper;
import bi.manager.facade.data.MBInventoryData;
import bi.manager.facade.facades.MBCategoryFacade;
import bi.manager.facade.facades.MBInventoryFacade;
import bi.uburaro.facade.data.ItemData;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component(value = "mBInventoryFacade")
public class DefaultMBInventoryFacade implements MBInventoryFacade {
    protected final MBInventoryService inventoryService;
    protected final InventoryMapper mapper;
    protected final FullInventoryMapper fullMapper;
    protected final MBCategoryFacade categoryFacade;

    public DefaultMBInventoryFacade(MBInventoryService inventoryService, InventoryMapper mapper, FullInventoryMapper fullMapper, MBCategoryFacade categoryFacade) {
        this.inventoryService = inventoryService;
        this.mapper = mapper;
        this.fullMapper = fullMapper;
        this.categoryFacade = categoryFacade;
    }


    @Override
    public MBInventoryData getInventoryByCode(final String code,final boolean allFields) {
        MBInventoryType inventory = inventoryService.getInventoryByCode(code);
        return allFields ? fullMapper.inventoryToData(inventory): mapper.inventoryToData(inventory);
    }

    @Override
    public Collection<MBInventoryData> getInventoriesByCategory(final String categoryCode,final boolean allFields) {
        Collection<MBInventoryType> inventories = inventoryService.getInventoriesByCategory(categoryCode);
        return allFields ? fullMapper.inventoriesToData(inventories) : mapper.inventoriesToData(inventories);
    }

    @Override
    public void updateInventory(final MBInventoryData inventory) {
        inventoryService.updateInventory(mapper.inventoryToType(inventory));
    }

    @Override
    public void createInventory(final MBInventoryData inventory) {
        inventoryService.updateInventory(mapper.inventoryToType(inventory));
    }

    @Override
    public void deleteInventory(final Set<String> codes) {
        inventoryService.deleteInventory(codes);
    }

    @Override
    public Collection<MBInventoryData> getInventoryByFacilityCode(String facilityCode) {
        return categoryFacade.getCategoriesByFacilityCode(facilityCode,true).stream()
                .filter(ItemData::isActive)
                .flatMap(category -> category.getInventories().stream())
                .filter(ItemData::isActive)
                .collect(Collectors.toList());
    }
}
