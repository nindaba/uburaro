package bi.uburaro.core.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static bi.uburaro.core.types.AddressType.CUSTOMER;
import static bi.uburaro.core.types.CompanyType.CUSTOMERS;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = CustomerType.ITEM_TYPE)
public class CustomerType extends PrincipalType{
    public static final String ITEM_TYPE = "customer";

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String PHONE = "phone";
    public static final String IDENTITY = "identity";
    public static final String NATIONALITY = "nationality";
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final String ADDRESS = "address";
    public static final String COMPANIES = "companies";

    private  String firstName;
    private  String lastName;
    private  String phone;
    private  String identity;
    private  String Nationality;
    private  String Age;
    private  String gender;
    @OneToMany
    private Set<AddressType> address;
    @ManyToMany
    @JoinTable(name = "customers2companies",
            joinColumns = {
                    @JoinColumn(name = "companies_date_created"),
                    @JoinColumn(name = "companies_item_type"),
                    @JoinColumn(name = "companies_t_key"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "customers_date_created"),
                    @JoinColumn(name = "customers_item_type"),
                    @JoinColumn(name = "customers_t_key"),
            })
    private Set<CompanyType> companies = new HashSet<>();

}
