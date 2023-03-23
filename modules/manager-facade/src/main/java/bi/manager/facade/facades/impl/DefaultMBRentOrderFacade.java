package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBRentOrderService;
import bi.manager.core.types.client.MBRentOrderType;
import bi.manager.core.utils.MBPage;
import bi.manager.facade.converters.order.RentOrderMapper;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.MBPageData;
import bi.manager.facade.data.MBPageableData;
import bi.manager.facade.data.MBRentOrderData;
import bi.manager.facade.facades.MBRentOrderFacade;
import bi.uburaro.facade.data.ItemData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import static bi.manager.facade.factories.MBDateUtil.getLocalDate;
import static bi.manager.facade.factories.PageFactory.createPage;

@Service(value = "mBRentOrderFacade")
public class DefaultMBRentOrderFacade implements MBRentOrderFacade {

    protected final MBRentOrderService service;
    protected final RentOrderMapper mapper;
    protected final RentOrderMapper rentOrderMapper;

    public DefaultMBRentOrderFacade(MBRentOrderService service, RentOrderMapper mapper, RentOrderMapper rentOrderMapper) {
        this.service = service;
        this.mapper = mapper;
        this.rentOrderMapper = rentOrderMapper;
    }

    @Override
    public Collection<MBRentOrderData> getOrderByFacilityCode(String code) {
        return mapper.rentsToData(service.getOrderByFacilityCode(code));
    }

    @Override
    public Collection<MBRentOrderData> getOrderByRentCode(String code) {
        return mapper.rentsToData(service.getOrderByRentCode(code));
    }

    @Override
    public Collection<MBRentOrderData> getOrderByClientCode(String code) {
        return mapper.rentsToData(service.getOrderByClientCode(code));
    }

    @Override
    public void placeOrder(MBRentOrderData order) {
        service.placeOrder(mapper.rentOrderToType(order));
    }

    @Override
    public void deleteOrder(Set<String> orderNumbers) {
        service.deleteOrder(orderNumbers);
    }

    @Override
    public Collection<MBRentOrderData> getOrdersByContract(String code) {
        return rentOrderMapper.rentsToData(
                service.getOrdersByContract(code));
    }

    @Override
    public MBPageData<MBRentOrderData> getOrderByFacilityCode(final String facility,final MBDateRangeData range, final MBPageableData pageable) {
        final LocalDate from = getLocalDate(range.getFrom());
        final LocalDate to = getLocalDate(range.getTo());
        final MBPage<MBRentOrderType> page = service.getOrderByFacilityCode(facility, from, to, createPage(pageable));
        final MBPageData<MBRentOrderData> pageData = new MBPageData<>();
        pageData.setContent(mapper.rentsToData(page.getContent()));
        pageData.setPages(page.getPages());
        return pageData;
    }
}
