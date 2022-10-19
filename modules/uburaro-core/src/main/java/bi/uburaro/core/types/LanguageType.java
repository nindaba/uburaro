package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.GroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = LanguageType.ITEM_TYPE)
public class LanguageType extends ItemType{
    public static final String ITEM_TYPE = "language";

    public static final String ISO_CODE = "isoCode";
    public static final String NAME = "name";

    private String isoCode;
    private String name;

}
