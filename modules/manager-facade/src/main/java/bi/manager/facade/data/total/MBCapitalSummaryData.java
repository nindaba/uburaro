package bi.manager.facade.data.total;

public class MBCapitalSummaryData {
    private long internalCapital;
    private long externalCapital;
    private long categoryExpenses;

    public long getInternalCapital() {
        return internalCapital;
    }

    public void setInternalCapital(long internalCapital) {
        this.internalCapital = internalCapital;
    }

    public long getExternalCapital() {
        return externalCapital;
    }

    public void setExternalCapital(long externalCapital) {
        this.externalCapital = externalCapital;
    }

    public long getCategoryExpenses() {
        return categoryExpenses;
    }

    public void setCategoryExpenses(long categoryExpenses) {
        this.categoryExpenses = categoryExpenses;
    }
}
