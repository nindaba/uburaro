package bi.manager.facade.data;

import bi.manager.core.types.enums.MBInventoryEntryEnum;
import bi.uburaro.facade.data.ItemData;

import java.util.Objects;

public class MBInventoryOrderData extends MBOrderData {
    private MBInventoryEntryEnum orderEntry;

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
        if (!super.equals(o)) return false;

        MBInventoryOrderData that = (MBInventoryOrderData) o;

        return orderEntry == that.orderEntry;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (orderEntry != null ? orderEntry.hashCode() : 0);
        return result;
    }
}
