package bi.manager.facade.data.jasper;

import bi.manager.facade.data.MBCapitalEntryData;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.total.MBCapitalSummaryData;

public class MBCapitalReportData {
    private MBCapitalSummaryData totals;
    private MBCapitalEntryData entries;
    private MBDateRangeData range;

    public MBCapitalSummaryData getTotals() {
        return totals;
    }

    public void setTotals(MBCapitalSummaryData totals) {
        this.totals = totals;
    }

    public MBCapitalEntryData getEntries() {
        return entries;
    }

    public void setEntries(MBCapitalEntryData entries) {
        this.entries = entries;
    }

    public MBDateRangeData getRange() {
        return range;
    }

    public void setRange(MBDateRangeData range) {
        this.range = range;
    }
}
