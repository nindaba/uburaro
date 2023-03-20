package bi.manager.web.controllers;

import bi.manager.facade.data.MBRentOrderData;
import bi.manager.facade.facades.MBRentContractFacade;
import bi.manager.facade.facades.MBRentOrderFacade;
import bi.manager.web.ManagerWebConstants;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

import static bi.manager.web.ManagerWebConstants.Controller.Orders.Rent.*;

@RestController
@RequestMapping(value = endpoint)
@CrossOrigin()
public class RentOrderController {
    @Resource(name = "mBRentOrderFacade")
    protected MBRentOrderFacade facade;

    @GetMapping(value = facilityOrders)
    Collection<MBRentOrderData> getOrderByFacilityCode(@PathVariable String code) {
        return facade.getOrderByFacilityCode(code);
    }

    @GetMapping(value = inventoryOrders)
    Collection<MBRentOrderData> getOrderByRentCode(@PathVariable String code) {
        return facade.getOrderByRentCode(code);
    }

    @GetMapping(value = clientOrders)
    Collection<MBRentOrderData> getOrderByClientCode(@PathVariable String code) {
        return facade.getOrderByClientCode(code);
    }
    @GetMapping(value = contractOrders)
    Collection<MBRentOrderData> getOrderByContract(@PathVariable String code) {
        return facade.getOrdersByContract(code);
    }

    @PostMapping
    void placeOrder(@RequestBody MBRentOrderData order) {
        facade.placeOrder(order);
    }

    @DeleteMapping
    void deleteOrder(@RequestParam Set<String> orderNumbers) {
        facade.deleteOrder(orderNumbers);
    }
}
