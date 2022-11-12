package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.AddressType;
import bi.uburaro.core.types.CompanyType;
import bi.uburaro.core.types.CustomerType;
import bi.uburaro.facade.converters.Converter;
import bi.uburaro.facade.converters.Converters;
import bi.uburaro.facade.data.AddressData;
import bi.uburaro.facade.data.CompanyData;
import bi.uburaro.facade.data.CustomerData;
import bi.uburaro.facade.pupulators.Populator;

public class FullCustomerPopulator implements Populator<CustomerType, CustomerData> {
    protected final Converter<AddressType, AddressData> addressConverter;
    protected final Converter<CompanyType, CompanyData> companyConverter;

    public FullCustomerPopulator(Converter<AddressType, AddressData> addressConverter, Converter<CompanyType, CompanyData> companyConverter) {
        this.addressConverter = addressConverter;
        this.companyConverter = companyConverter;
    }

    @Override
    public void populate(final CustomerType source, final CustomerData target) {
        target.setAddress(Converters.convertAll(addressConverter,source.getAddress()));
        target.setCompanies(Converters.convertAll(companyConverter,source.getCompanies()));
    }
}