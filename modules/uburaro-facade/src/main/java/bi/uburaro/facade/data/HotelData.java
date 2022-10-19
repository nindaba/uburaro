package bi.uburaro.facade.data;

import bi.uburaro.facade.data.groups.GroupData;
import bi.uburaro.facade.data.groups.TaxGroupData;

import java.util.Collection;

public class HotelData extends ItemData{
    private String name;
    private String alias;
    private Collection<RoomData> rooms;
    private Collection<LanguageData> languages;
    private LanguageData defaultLanguage;
    private Collection<AddressData> addresses;
    private Collection<GroupData> groups;
    private Collection<TaxGroupData> taxGroups;
    private BranchGroupData branchGroup;
    private TaxGroupData defaultTaxGroup;

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

    public Collection<RoomData> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<RoomData> rooms) {
        this.rooms = rooms;
    }

    public Collection<LanguageData> getLanguages() {
        return languages;
    }

    public void setLanguages(Collection<LanguageData> languages) {
        this.languages = languages;
    }

    public LanguageData getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(LanguageData defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public Collection<AddressData> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<AddressData> addresses) {
        this.addresses = addresses;
    }


    public void setBranchGroup(BranchGroupData branchGroup) {
        this.branchGroup = branchGroup;
    }

    public BranchGroupData getBranchGroup() {
        return branchGroup;
    }

    public Collection<GroupData> getGroups() {
        return groups;
    }

    public void setGroups(Collection<GroupData> groups) {
        this.groups = groups;
    }

    public Collection<TaxGroupData> getTaxGroups() {
        return taxGroups;
    }

    public void setTaxGroups(Collection<TaxGroupData> taxGroups) {
        this.taxGroups = taxGroups;
    }

    public void setDefaultTaxGroup(TaxGroupData defaultTaxGroup) {
        this.defaultTaxGroup = defaultTaxGroup;
    }

    public TaxGroupData getDefaultTaxGroup() {
        return defaultTaxGroup;
    }
}
