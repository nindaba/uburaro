package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBInventoryOrderService;
import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.enums.MBInventoryEntryEnum;
import bi.manager.core.utils.MBPage;
import bi.manager.facade.converters.order.InventoryOrderMapper;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.MBInventoryOrderData;
import bi.manager.facade.data.MBPageData;
import bi.manager.facade.data.MBPageableData;
import bi.manager.facade.facades.MBInventoryOrderFacade;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

import static bi.manager.facade.factories.MBDateUtil.getLocalDate;
import static bi.manager.facade.factories.PageFactory.createPage;

@Service(value = "mBInventoryOrderFacade")
public class DefaultMBInventoryOrderFacade implements MBInventoryOrderFacade {
    protected final MBInventoryOrderService orderService;
    protected final InventoryOrderMapper mapper;

    public DefaultMBInventoryOrderFacade(MBInventoryOrderService orderService, InventoryOrderMapper inventoryOrderMapper) {
        this.orderService = orderService;
        this.mapper = inventoryOrderMapper;
    }

    @Override
    public Collection<MBInventoryOrderData> getOrderByFacilityCode(final String code) {
        return mapper.inventoriesToData(
                orderService.getOrderByFacilityCode(code)
        );
    }

    @Override
    public Collection<MBInventoryOrderData> getOrderByInventoryCode(final String code) {
        return mapper.inventoriesToData(
                orderService.getOrderByInventoryCode(code)
        );
    }

    @Override
    public Collection<MBInventoryOrderData> getOrderByClientCode(final String code) {
        return mapper.inventoriesToData(
                orderService.getOrderByClientCode(code)
        );
    }

    @Override
    public void placeOrder(final MBInventoryOrderData order) {
        orderService.placeOrder(mapper.inventoryOrderToType(order));
    }

    @Override
    public void deleteOrder(final Set<String> orderNumbers) {
        orderService.deleteOrder(orderNumbers);
    }

    @Override
    public MBPageData<MBInventoryOrderData> getOrderByFacilityCode(final String code, final MBInventoryEntryEnum orderType, final MBDateRangeData range, final MBPageableData pageable) {
        final MBPageData<MBInventoryOrderData> pageData = new MBPageData<>();
        final MBPage<MBInventoryOrderType> page = orderService.getOrderByFacilityCode(code,orderType, getLocalDate(range.getFrom()), getLocalDate(range.getTo()), createPage(pageable));
        pageData.setContent(mapper.inventoriesToData(page.getContent()));
        pageData.setPages(page.getPages());
        return pageData;
    }
}
