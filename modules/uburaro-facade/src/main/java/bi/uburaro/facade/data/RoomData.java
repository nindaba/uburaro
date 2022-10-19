package bi.uburaro.facade.data;

import bi.uburaro.facade.data.groups.PriceGroupData;
import bi.uburaro.facade.data.groups.RoomGroupData;
import bi.uburaro.facade.data.groups.TaxGroupData;

import java.util.Collection;

public class RoomData extends ItemData{
    private String roomNumber;
    private Collection<RoomGroupData> roomGroups;
    private Collection<PriceGroupData> priceGroups;
    private Collection<TaxGroupData> taxGroups;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Collection<RoomGroupData> getRoomGroups() {
        return roomGroups;
    }

    public void setRoomGroups(Collection<RoomGroupData> roomGroups) {
        this.roomGroups = roomGroups;
    }

    public Collection<PriceGroupData> getPriceGroups() {
        return priceGroups;
    }

    public void setPriceGroups(Collection<PriceGroupData> priceGroups) {
        this.priceGroups = priceGroups;
    }

    public Collection<TaxGroupData> getTaxGroups() {
        return taxGroups;
    }

    public void setTaxGroups(Collection<TaxGroupData> taxGroups) {
        this.taxGroups = taxGroups;
    }
}
