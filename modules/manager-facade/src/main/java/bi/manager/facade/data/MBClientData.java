package bi.manager.facade.data;

import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.core.types.client.MBRentOrderType;
import bi.uburaro.facade.data.ItemData;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MBClientData extends ItemData {
    private String name;
    private String address;
    private long totalDebt;
    private MBFacilityData facility;
    private Set<MBOrderData> orders =  new HashSet<>();
    private Set<MBInvoiceData> invoices =  new HashSet<>();
    private Set<MBRentContractData> contracts = new HashSet<>();

    public Set<MBRentContractData> getContracts() {
        return contracts;
    }

    public void setContracts(Set<MBRentContractData> contracts) {
        this.contracts = contracts;
    }

    public long getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(long totalDebt) {
        this.totalDebt = totalDebt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MBOrderData> getOrders() {
        return orders;
    }

    public Set<MBInvoiceData> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<MBInvoiceData> invoices) {
        this.invoices = invoices;
    }

    public void setOrders(Set<MBOrderData> orders) {
        this.orders = orders;
    }

    public MBFacilityData getFacility() {
        return facility;
    }

    public void setFacility(MBFacilityData facility) {
        this.facility = facility;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MBClientData)) return false;

        MBClientData that = (MBClientData) o;

        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
