package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBCapitalService;
import bi.manager.core.services.MBInventoryOrderService;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.manager.core.types.enums.MBInventoryEntryEnum;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.total.MBCapitalSummaryData;
import bi.manager.facade.facades.MBTotalSummaryFacade;
import org.springframework.stereotype.Service;

import static bi.manager.facade.factories.MBDateUtil.getLocalDate;

@Service(value = "mBTotalSummaryFacade")
public class DefaultMBTotalSummaryFacade implements MBTotalSummaryFacade {

    protected final MBInventoryOrderService inventoryOrderService;
    protected final MBCapitalService capitalService;

    public DefaultMBTotalSummaryFacade(MBInventoryOrderService inventoryOrderService, MBCapitalService capitalService) {
        this.inventoryOrderService = inventoryOrderService;
        this.capitalService = capitalService;
    }


    @Override
    public MBCapitalSummaryData getCapitalSummary(final String facility, final MBDateRangeData range) {
        final long internal = capitalService.getTotalAmount(facility, range.getFrom(), range.getTo(), MBEntryEnum.INTERNAL);
        final long external = capitalService.getTotalAmount(facility, range.getFrom(), range.getTo(), MBEntryEnum.EXTERNAL);
        final long inventoryExpenses = inventoryOrderService.getTotalAmount(facility, getLocalDate(range.getFrom()), getLocalDate(range.getTo()), MBInventoryEntryEnum.REFILL);
        final MBCapitalSummaryData summaryData = new MBCapitalSummaryData();

        summaryData.setExternalCapital(external);
        summaryData.setInternalCapital(internal);
        summaryData.setCategoryExpenses(inventoryExpenses);
        return summaryData;
    }
}
