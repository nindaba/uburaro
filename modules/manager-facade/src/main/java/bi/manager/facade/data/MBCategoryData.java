package bi.manager.facade.data;


import bi.uburaro.facade.data.ItemData;

import java.util.HashSet;
import java.util.Set;

public class MBCategoryData extends ItemData {
    private String name;
    private Set<MBInventoryData> inventories = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MBInventoryData> getInventories() {
        return inventories;
    }

    public void setInventories(Set<MBInventoryData> inventories) {
        this.inventories = inventories;
    }
}
