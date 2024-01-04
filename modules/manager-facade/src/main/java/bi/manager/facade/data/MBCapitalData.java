package bi.manager.facade.data;

import bi.manager.core.types.MBCapitalEntryType;
import bi.uburaro.facade.data.ItemData;

import java.util.HashSet;
import java.util.Set;

public class MBCapitalData extends ItemData {
    private long currentValue;
    private Set<MBCapitalEntryData> entries = new HashSet<>();

    public long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(long currentValue) {
        this.currentValue = currentValue;
    }

    public Set<MBCapitalEntryData> getEntries() {
        return entries;
    }

    public void setEntries(Set<MBCapitalEntryData> entries) {
        this.entries = entries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MBCapitalData)) return false;

        MBCapitalData that = (MBCapitalData) o;

        return currentValue == that.currentValue;
    }

    @Override
    public int hashCode() {
        return (int) (currentValue ^ (currentValue >>> 32));
    }
}
