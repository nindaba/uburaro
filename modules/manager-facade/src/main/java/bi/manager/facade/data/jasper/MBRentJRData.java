package bi.manager.facade.data.jasper;

import bi.manager.facade.data.MBRentContractData;
import bi.manager.facade.data.MBRentOrderData;

import java.util.Collection;

public class MBRentJRData extends MBReportData{
    private Collection<MBRentContractData> contracts;
    private Collection<MBRentOrderData> orders;

    public Collection<MBRentContractData> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<MBRentContractData> contracts) {
        this.contracts = contracts;
    }

    public Collection<MBRentOrderData> getOrders() {
        return orders;
    }

    public void setOrders(Collection<MBRentOrderData> orders) {
        this.orders = orders;
    }
}
