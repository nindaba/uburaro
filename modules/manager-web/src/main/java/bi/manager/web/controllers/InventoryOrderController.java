package bi.manager.web.controllers;

import bi.manager.facade.data.MBInventoryOrderData;
import bi.manager.facade.facades.MBInventoryOrderFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

import static bi.manager.web.ManagerWebConstants.Controller.Orders.*;

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
