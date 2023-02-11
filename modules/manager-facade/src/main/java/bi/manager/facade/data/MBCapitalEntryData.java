package bi.manager.facade.data;

import bi.uburaro.facade.data.ItemData;

import java.util.Date;
import java.util.Objects;

public class MBCapitalEntryData extends ItemData {
    private Long amount;
    private String entryType;
    private Date dateCreated;


    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getEntryType() {
        return entryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MBCapitalEntryData)) return false;

        MBCapitalEntryData that = (MBCapitalEntryData) o;

        if (!Objects.equals(amount, that.amount)) return false;
        return entryType == that.entryType;
    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (entryType != null ? entryType.hashCode() : 0);
        return result;
    }
}
