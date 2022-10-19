package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.GroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = AddressType.ITEM_TYPE)
public class AddressType extends ItemType {
    public static final String ITEM_TYPE = "address";
    public static final String STREET = "street";
    public static final String HOUSE_NUMBER = "houseNumber";
    public static final String POST_CODE = "postCode";
    public static final String COUNTRY = "country";
    public static final String PROVINCE = "province";
    public static final String DISTRICT = "district";


    private String country;
    private String province;
    private String district;
    private String street;
    private String postCode;
    private String houseNumber;
}
