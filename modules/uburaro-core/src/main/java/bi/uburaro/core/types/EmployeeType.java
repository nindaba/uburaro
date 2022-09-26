package bi.uburaro.core.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class EmployeeType extends PrincipalType{
    public static final String ITEM_TYPE = "employee";
    public static final String LASTNAME = "lastname";
    public static final String SURNAME = "surname";

    private String lastname;
    private String surname;

}
