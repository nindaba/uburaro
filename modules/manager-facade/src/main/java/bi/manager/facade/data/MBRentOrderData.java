package bi.manager.facade.data;

import bi.uburaro.facade.data.ItemData;

import java.time.LocalDate;
import java.util.Objects;

public class MBRentOrderData extends MBOrderData {
    private LocalDate from;
    private LocalDate to;
    private int unitCharged;
    private int totalUnitCharged;
    private int units;

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

    public int getUnitCharged() {
        return unitCharged;
    }

    public void setUnitCharged(int unitCharged) {
        this.unitCharged = unitCharged;
    }

    public int getTotalUnitCharged() {
        return totalUnitCharged;
    }

    public void setTotalUnitCharged(int totalUnitCharged) {
        this.totalUnitCharged = totalUnitCharged;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MBRentOrderData)) return false;
        if (!super.equals(o)) return false;

        MBRentOrderData that = (MBRentOrderData) o;

        if (unitCharged != that.unitCharged) return false;
        if (totalUnitCharged != that.totalUnitCharged) return false;
        if (units != that.units) return false;
        if (!Objects.equals(from, that.from)) return false;
        return Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + unitCharged;
        result = 31 * result + totalUnitCharged;
        result = 31 * result + units;
        return result;
    }
}
