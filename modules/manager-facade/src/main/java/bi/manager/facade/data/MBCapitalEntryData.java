package bi.manager.facade.data;

import bi.uburaro.facade.data.ItemData;

import java.util.Date;
import java.util.Objects;

public class MBCapitalEntryData extends ItemData {
    private Long amount;
    private String entryType;
    private String description;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MBCapitalEntryData)) return false;

        MBCapitalEntryData that = (MBCapitalEntryData) o;

        if (!Objects.equals(amount, that.amount)) return false;
        if (!Objects.equals(entryType, that.entryType)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (entryType != null ? entryType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
