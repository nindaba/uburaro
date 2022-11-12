package bi.uburaro.facade.facades;

import bi.uburaro.facade.data.CustomerData;

import java.util.Collection;
import java.util.List;

public interface CustomerFacade {

    /**
     * Gets all customers
     *
     * @param allFields determines if all the attributes will be included
     * @return Collection of Customers
     */
    Collection<CustomerData> getAllCustomers(boolean allFields);

    /**
     * Gets Hotel's Customers based on Hotel code
     *
     * @param allFields determines if all the attributes will be included
     * @param code      of the hotel
     * @return Collection of Customers
     */
    Collection<CustomerData> getHotelCustomers(String code, boolean allFields);

    /**
     * Gets a customer by code or customerUid
     *
     * @param allFields determines if all the attributes will be included
     * @param code
     * @return Customer
     */
    CustomerData getCustomerByCode(String code, boolean allFields);

    /**
     * Adds the customer to the company if the customer doesn't belong to the company already
     * otherwise the customer is removed from the company in the <i>companyCodes</i>
     *
     * @param companyCodes
     * @param customerCode
     */
    void updateCompanies(String customerCode, List<String> companyCodes);


    /**
     * Deletes a customer
     * @param code of the customer to be removed
     */
    void deleteCustomer(String code);
}
