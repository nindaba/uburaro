package bi.manager.facade.data.jasper;

import bi.manager.facade.data.MBRentContractData;
import bi.manager.facade.data.MBRentOrderData;

import java.util.Collection;

public class MBRentJRData extends MBReportData{
    private Collection<MBRentContractData> contracts;
    private Collection<MBRentOrderData> paidOrders;
    private Collection<MBRentOrderData> unPaidOrders;

    public Collection<MBRentContractData> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<MBRentContractData> contracts) {
        this.contracts = contracts;
    }

    public Collection<MBRentOrderData> getPaidOrders() {
        return paidOrders;
    }

    public void setPaidOrders(Collection<MBRentOrderData> paidOrders) {
        this.paidOrders = paidOrders;
    }

    public Collection<MBRentOrderData> getUnPaidOrders() {
        return unPaidOrders;
    }

    public void setUnPaidOrders(Collection<MBRentOrderData> unPaidOrders) {
        this.unPaidOrders = unPaidOrders;
    }
}
