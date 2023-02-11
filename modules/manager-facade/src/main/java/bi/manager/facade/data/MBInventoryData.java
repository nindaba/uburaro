package bi.manager.facade.data;


import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBInventoryOrderType;
import bi.uburaro.facade.data.ItemData;

import java.util.HashSet;
import java.util.Set;

public class MBInventoryData extends ItemData {
    private Set<MBInventoryOrderData> inventoryOrders =  new HashSet<>();
    private MBCategoryData category;

    private String name;
    private Integer quantity;
    private Long cost;
}
