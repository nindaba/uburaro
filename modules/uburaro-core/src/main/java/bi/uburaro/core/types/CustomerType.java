package bi.uburaro.core.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

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
    private Set<CompanyType> companies;

}
