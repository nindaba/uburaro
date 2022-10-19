package bi.uburaro.core.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = PrincipalType.ITEM_TYPE)
public class PrincipalType extends ItemType{
    public static final String ITEM_TYPE = "principal";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String USERGROUPS = "usergroups";

    private String username;
    private String password;
//    private Set<GroupType> groups;

}
