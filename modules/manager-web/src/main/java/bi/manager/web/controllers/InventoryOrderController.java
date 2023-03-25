package bi.manager.web.controllers;

import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.client.MBOrderType;
import bi.manager.core.types.enums.MBInventoryEntryEnum;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.MBInventoryOrderData;
import bi.manager.facade.data.MBPageData;
import bi.manager.facade.facades.MBInventoryOrderFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

import static bi.manager.facade.factories.PageFactory.createPage;
import static bi.manager.web.ManagerWebConstants.Controller.Orders.Inventory;

@RestController
@RequestMapping(value = Inventory.endpoint)
@CrossOrigin()
public class InventoryOrderController {
    @Resource(name = "mBInventoryOrderFacade")
    protected MBInventoryOrderFacade facade;

    @GetMapping(value = Inventory.facilityOrders)
    Collection<MBInventoryOrderData> getOrderByFacilityCode(@PathVariable String code) {
        return facade.getOrderByFacilityCode(code);
    }

    @PostMapping(value = Inventory.facilityOrders)
    MBPageData<MBInventoryOrderData> getOrderByFacilityCode(@PathVariable String code,
                                                            @RequestBody MBDateRangeData range,
                                                            @RequestParam MBInventoryEntryEnum orderEntry,
                                                            @RequestParam(required = false, defaultValue = "100") int pageSize,
                                                            @RequestParam(required = false, defaultValue = "0") int currentPage,
                                                            @RequestParam(required = false, defaultValue = MBOrderType.ORDER_DATE) String sort,
                                                            @RequestParam(required = false, defaultValue = "asc") String sortOrder) {

        return facade.getOrderByFacilityCode(code,orderEntry, range, createPage(pageSize, currentPage, sort, sortOrder));
    }

    @GetMapping(value = Inventory.inventoryOrders)
    Collection<MBInventoryOrderData> getOrderByInventoryCode(@PathVariable String code) {
        return facade.getOrderByInventoryCode(code);
    }

    @GetMapping(value = Inventory.clientOrders)
    Collection<MBInventoryOrderData> getOrderByClientCode(@PathVariable String code) {
        return facade.getOrderByClientCode(code);
    }

    @PostMapping
    void placeOrder(@RequestBody MBInventoryOrderData order) {
        facade.placeOrder(order);
    }

    @DeleteMapping
    void deleteOrder(@RequestParam Set<String> orderNumbers) {
        facade.deleteOrder(orderNumbers);
    }
}
