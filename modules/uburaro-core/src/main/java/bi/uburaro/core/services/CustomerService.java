package bi.uburaro.core.services;

import bi.uburaro.core.types.CustomerType;
import bi.uburaro.core.validators.impl.CustomerCompanyValidator;

import java.util.Collection;
import java.util.List;

public interface CustomerService {
    /**
     * Gets all customers
     *
     * @return Collection of Customers
     */
    Collection<CustomerType> getAllCustomers();

    /**
     * Gets Hotel's Customers based on Hotel code
     *
     * @param code of the hotel
     * @return Collection of Customers
     */
    Collection<CustomerType> getHotelCustomers(String code);

    /**
     * Gets a customer by code or customerUid
     *
     * @param code
     * @return Customer
     */
    CustomerType getCustomerByCode(String code);

    /**
     * Removes the customer from the company if the customer already exist in the company
     * otherwise the customer gets removed
     *
     * @param customerCode
     * @param companyCodes
     * @implNote Customer will never be removed from the default company.
     * @see CustomerCompanyValidator
     */
    void updateCompanies(String customerCode, List<String> companyCodes);
}
