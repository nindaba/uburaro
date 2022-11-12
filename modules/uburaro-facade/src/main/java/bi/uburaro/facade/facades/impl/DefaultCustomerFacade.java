package bi.uburaro.facade.facades.impl;

import bi.uburaro.core.services.CustomerService;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.CustomerType;
import bi.uburaro.facade.converters.Converter;
import bi.uburaro.facade.converters.Converters;
import bi.uburaro.facade.data.CustomerData;
import bi.uburaro.facade.facades.CustomerFacade;

import java.util.Collection;
import java.util.List;

public class DefaultCustomerFacade implements CustomerFacade {
    protected final CustomerService customerService;
    protected final Converter<CustomerType,CustomerData> converter;
    protected final Converter<CustomerType,CustomerData> fullConverter;
    protected final TypeService typeService;

    public DefaultCustomerFacade(CustomerService customerService, Converter<CustomerType,CustomerData> converter, Converter<CustomerType,CustomerData> fullConverter, TypeService typeService) {
        this.customerService = customerService;
        this.converter = converter;
        this.fullConverter = fullConverter;
        this.typeService = typeService;
    }

    @Override
    public Collection<CustomerData> getAllCustomers(boolean allFields) {
        return Converters.convertAll(
                allFields ? fullConverter : converter,
                customerService.getAllCustomers());
    }

    @Override
    public Collection<CustomerData> getHotelCustomers(String code, boolean allFields) {
        return Converters.convertAll(
                allFields ? fullConverter : converter,
                customerService.getHotelCustomers(code));
    }

    @Override
    public CustomerData getCustomerByCode(String code, boolean allFields) {
        return Converters.convert(
                allFields ? fullConverter : converter,
                customerService.getCustomerByCode(code));
    }

    @Override
    public void updateCompanies(String customerCode, List<String> companyCodes) {
        customerService.updateCompanies(customerCode,companyCodes);
    }

    @Override
    public void deleteCustomer(String code) {
        typeService.delete(code,CustomerType.class);
    }

}
