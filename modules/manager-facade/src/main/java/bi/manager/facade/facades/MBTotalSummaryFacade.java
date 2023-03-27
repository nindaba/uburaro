package bi.manager.facade.facades;

import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.total.MBCapitalSummaryData;

public interface MBTotalSummaryFacade {
    /**
     * Get Capital Totals Summary for {@code facility} within {@code  range}
     *
     * @param facility
     * @param range
     *
     * @return capital summary
     */
    MBCapitalSummaryData getCapitalSummary(String facility, MBDateRangeData range);
}
