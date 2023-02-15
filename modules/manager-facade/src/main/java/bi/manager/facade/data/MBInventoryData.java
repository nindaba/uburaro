package bi.manager.facade.data;


import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBInventoryOrderType;
import bi.uburaro.facade.data.ItemData;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MBInventoryData extends ItemData {
    private Set<MBInventoryOrderData> inventoryOrders =  new HashSet<>();
    private MBCategoryData category;

    private String name;
    private Integer quantity;
    private Long cost;

    public Set<MBInventoryOrderData> getInventoryOrders() {
        return inventoryOrders;
    }

    public void setInventoryOrders(Set<MBInventoryOrderData> inventoryOrders) {
        this.inventoryOrders = inventoryOrders;
    }

    public MBCategoryData getCategory() {
        return category;
    }

    public void setCategory(MBCategoryData category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MBInventoryData)) return false;

        MBInventoryData that = (MBInventoryData) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(quantity, that.quantity)) return false;
        return Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }
}
