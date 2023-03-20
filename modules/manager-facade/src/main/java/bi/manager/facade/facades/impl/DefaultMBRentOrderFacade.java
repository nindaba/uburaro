package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBRentOrderService;
import bi.manager.facade.converters.order.RentOrderMapper;
import bi.manager.facade.data.MBRentOrderData;
import bi.manager.facade.facades.MBRentOrderFacade;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

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
}
