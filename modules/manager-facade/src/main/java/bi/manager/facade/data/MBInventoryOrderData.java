package bi.manager.facade.data;

import bi.manager.core.types.enums.MBInventoryEntryEnum;
import bi.uburaro.facade.data.ItemData;

import java.util.Objects;

public class MBInventoryOrderData extends ItemData {
    private String orderNumber;

    private MBInventoryData inventory;

    private MBInventoryEntryEnum orderEntry;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public MBInventoryData getInventory() {
        return inventory;
    }

    public void setInventory(MBInventoryData inventory) {
        this.inventory = inventory;
    }

    public MBInventoryEntryEnum getOrderEntry() {
        return orderEntry;
    }

    public void setOrderEntry(MBInventoryEntryEnum orderEntry) {
        this.orderEntry = orderEntry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MBInventoryOrderData)) return false;

        MBInventoryOrderData that = (MBInventoryOrderData) o;

        if (!Objects.equals(orderNumber, that.orderNumber)) return false;
        return orderEntry == that.orderEntry;
    }

    @Override
    public int hashCode() {
        int result = orderNumber != null ? orderNumber.hashCode() : 0;
        result = 31 * result + (orderEntry != null ? orderEntry.hashCode() : 0);
        return result;
    }
}
