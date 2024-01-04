package bi.manager.facade.data;


import bi.uburaro.facade.data.ItemData;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MBFacilityData extends ItemData {
    private String name;
    private String alias;
    private String address;
    private Set<MBCategoryData> categories = new HashSet<>();
    private Set<MBClientData> clients = new HashSet<>();
    private MBCapitalData capital;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<MBCategoryData> getCategories() {
        return categories;
    }

    public void setCategories(Set<MBCategoryData> categories) {
        this.categories = categories;
    }

    public Set<MBClientData> getClients() {
        return clients;
    }

    public void setClients(Set<MBClientData> clients) {
        this.clients = clients;
    }

    public MBCapitalData getCapital() {
        return capital;
    }

    public void setCapital(MBCapitalData capital) {
        this.capital = capital;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MBFacilityData)) return false;

        MBFacilityData that = (MBFacilityData) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(alias, that.alias)) return false;
        if (!Objects.equals(address, that.address)) return false;
        return Objects.equals(capital, that.capital);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (capital != null ? capital.hashCode() : 0);
        return result;
    }
}
