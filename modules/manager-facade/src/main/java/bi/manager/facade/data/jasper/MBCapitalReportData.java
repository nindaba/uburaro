package bi.manager.facade.data.jasper;

import bi.manager.facade.data.MBCapitalEntryData;
import bi.manager.facade.data.total.MBCapitalSummaryData;

import java.util.Collection;

public class MBCapitalReportData extends MBReportData{
    private MBCapitalSummaryData totals;
    private Collection<MBCapitalEntryData> entries;

    public MBCapitalSummaryData getTotals() {
        return totals;
    }

    public void setTotals(MBCapitalSummaryData totals) {
        this.totals = totals;
    }

    public Collection<MBCapitalEntryData> getEntries() {
        return entries;
    }

    public void setEntries(Collection<MBCapitalEntryData> entries) {
        this.entries = entries;
    }
}
