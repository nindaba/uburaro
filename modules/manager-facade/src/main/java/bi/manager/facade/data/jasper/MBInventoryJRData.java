package bi.manager.facade.data.jasper;

import bi.manager.facade.data.MBInventoryOrderData;
import bi.manager.facade.data.MBInventoryOrderData;

import java.util.Collection;

public class MBInventoryJRData extends MBReportData{
    private Collection<MBInventoryOrderData> refillOrders;
    private Collection<MBInventoryOrderData> outOrders;
    private Collection<MBInventoryOrderData> soldOrders;

    public Collection<MBInventoryOrderData> getRefillOrders() {
        return refillOrders;
    }

    public void setRefillOrders(Collection<MBInventoryOrderData> refillOrders) {
        this.refillOrders = refillOrders;
    }

    public Collection<MBInventoryOrderData> getOutOrders() {
        return outOrders;
    }

    public void setOutOrders(Collection<MBInventoryOrderData> outOrders) {
        this.outOrders = outOrders;
    }

    public Collection<MBInventoryOrderData> getSoldOrders() {
        return soldOrders;
    }

    public void setSoldOrders(Collection<MBInventoryOrderData> soldOrders) {
        this.soldOrders = soldOrders;
    }
}
