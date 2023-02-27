package bi.manager.facade.data;

import bi.uburaro.facade.data.ItemData;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MBRentContractData extends ItemData {
    private LocalDate from;
    private LocalDate to;
    private long costPerUnit;
    private int unit;
    private String ContractFileName;

    public String getContractFileName() {
        return ContractFileName;
    }

    public void setContractFileName(String ContractFileName) {
        this.ContractFileName = ContractFileName;
    }

    private NamedItemData property;

    private NamedItemData client;

    private Set<MBRentOrderData> orders = new HashSet<>();

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public long getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(long costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public NamedItemData getProperty() {
        return property;
    }

    public void setProperty(NamedItemData property) {
        this.property = property;
    }

    public NamedItemData getClient() {
        return client;
    }

    public void setClient(NamedItemData client) {
        this.client = client;
    }

    public Set<MBRentOrderData> getOrders() {
        return orders;
    }

    public void setOrders(Set<MBRentOrderData> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MBRentContractData)) return false;

        MBRentContractData that = (MBRentContractData) o;

        if (costPerUnit != that.costPerUnit) return false;
        if (unit != that.unit) return false;
        if (!Objects.equals(from, that.from)) return false;
        if (!Objects.equals(to, that.to)) return false;
        return Objects.equals(ContractFileName, that.ContractFileName);
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (int) (costPerUnit ^ (costPerUnit >>> 32));
        result = 31 * result + unit;
        result = 31 * result + (ContractFileName != null ? ContractFileName.hashCode() : 0);
        return result;
    }
}
