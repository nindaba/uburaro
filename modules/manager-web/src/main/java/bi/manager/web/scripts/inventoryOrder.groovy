package bi.manager.web.scripts

import bi.manager.core.types.enums.MBInventoryEntryEnum
import bi.manager.facade.data.MBInventoryOrderData

void createOrder(String code,int quantity,long cost) {
    var order = new MBInventoryOrderData();
    order.setOrderEntry(MBInventoryEntryEnum.REFILL);
    order.setItemCode(code);
    order.setQuantity(quantity);
    order.setCost(cost);
    placeOrder(order);
}

//select concat(concat(concat(CONCAT("createOrder(\"",code),"\","),concat(quantity,",")),concat(`cost`,");")) from mbinventory as i inner join item as t WHERE i.t_key = t.t_key and quantity > 0;
