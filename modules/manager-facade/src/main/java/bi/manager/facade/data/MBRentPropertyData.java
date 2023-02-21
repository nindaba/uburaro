package bi.manager.facade.data;

import bi.uburaro.facade.data.ItemData;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MBRentPropertyData extends ItemData {
    private Set<MBRentOrderData> rentOrders = new HashSet<>();
    private MBClientData currentClient;
    private MBFacilityData facility;

    private String name;
    private String unit;
    private long cost;
    private String address;

    public Set<MBRentOrderData> getRentOrders() {
        return rentOrders;
    }

    public void setRentOrders(Set<MBRentOrderData> rentOrders) {
        this.rentOrders = rentOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MBRentPropertyData)) return false;

        MBRentPropertyData that = (MBRentPropertyData) o;

        if (cost != that.cost) return false;
        if (!Objects.equals(currentClient, that.currentClient))
            return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(unit, that.unit)) return false;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        int result = currentClient != null ? currentClient.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (int) (cost ^ (cost >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public MBClientData getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(MBClientData currentClient) {
        this.currentClient = currentClient;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
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

}
