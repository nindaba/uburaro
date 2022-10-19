package bi.uburaro.core.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = CustomerType.ITEM_TYPE)
public class CustomerType extends PrincipalType{
    public static final String ITEM_TYPE = "customer";
    public static final String ADDRESS = "address";

    @OneToMany
    private List<AddressType> addressType;
}
