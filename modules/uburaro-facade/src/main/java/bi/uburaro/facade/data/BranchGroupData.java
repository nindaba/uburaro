package bi.uburaro.facade.data;

import bi.uburaro.facade.data.groups.GroupData;

import java.util.Collection;

public class BranchGroupData extends GroupData {
    private Collection<HotelData> members;

    public Collection<HotelData> getMembers() {
        return members;
    }

    public void setMembers(Collection<HotelData> members) {
        this.members = members;
    }
}
