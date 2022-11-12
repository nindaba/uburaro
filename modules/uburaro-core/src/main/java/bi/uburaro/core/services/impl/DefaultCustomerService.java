package bi.uburaro.core.services.impl;

import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.*;
import bi.uburaro.core.types.CompanyType;
import bi.uburaro.core.types.CustomerType;
import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.utils.MessageUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
public class DefaultCustomerService implements CustomerService {

    private final TypeService typeService;
    private final SessionService sessionService;
    private final HotelService hotelService;

    public DefaultCustomerService(TypeService typeService, SessionService sessionService, HotelService hotelService) {
        this.typeService = typeService;
        this.sessionService = sessionService;
        this.hotelService = hotelService;
    }

    @Override
    public Collection<CustomerType> getAllCustomers() {
        return typeService.findAll(CustomerType.class);
    }

    @Override
    public Collection<CustomerType> getHotelCustomers(String code) {
        return Optional.ofNullable(sessionService.getCurrentHotel())
                .or(() -> Optional.ofNullable(hotelService.getHotelByCode(code)))
                .map(HotelType::getBranchGroup).stream()
                .peek(branch ->
                        branch.getPrincipals().removeIf(principal ->
                                StringUtils.equals(principal.getPrimaryKey().getItemType(), CustomerType.class.getName())))
                .map(branch -> (CustomerType) branch.getPrincipals())
                .collect(Collectors.toList());
    }

    @Override
    public CustomerType getCustomerByCode(String code) {
        return typeService.findItemByCode(code, CustomerType.class);
    }

    @Override
    public void updateCompanies(final String customerCode, final List<String> companyCodes) {
        final CustomerType customer = typeService.findItemByCode(customerCode, CustomerType.class);

        if (customer == null){
            throw new NotFoundException(MessageUtils.format("No Customer of code {} was found", customerCode));
        }

        customer.getCompanies()
                .removeIf(company -> companyCodes.removeIf(code-> StringUtils.equals(code, company.getCode())));

        companyCodes.stream()
                .map(code -> typeService.findItemByCode(code,CompanyType.class))
                .filter(Objects::nonNull)
                .forEach(company -> customer.getCompanies().add(company));

        typeService.save(customer);
    }
}
