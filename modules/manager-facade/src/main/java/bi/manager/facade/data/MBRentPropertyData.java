package bi.manager.facade.data;

import bi.uburaro.facade.data.ItemData;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MBRentPropertyData extends ItemData {
    private Set<MBRentContractData> contracts = new HashSet<>();
    private MBRentContractData currentContract;
    private MBFacilityData facility;

    private String name;
    private int unit;
    private long cost;
    private String address;

    public Set<MBRentContractData> getContracts() {
        return contracts;
    }

    public void setContracts(Set<MBRentContractData> contracts) {
        this.contracts = contracts;
    }

    public MBRentContractData getCurrentContract() {
        return currentContract;
    }

    public void setCurrentContract(MBRentContractData currentContract) {
        this.currentContract = currentContract;
    }

    public MBFacilityData getFacility() {
        return facility;
    }

    public void setFacility(MBFacilityData facility) {
        this.facility = facility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
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
        if (!(o instanceof MBRentPropertyData)) return false;

        MBRentPropertyData that = (MBRentPropertyData) o;

        if (unit != that.unit) return false;
        if (cost != that.cost) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + unit;
        result = 31 * result + (int) (cost ^ (cost >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
