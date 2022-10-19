package bi.uburaro.core.types.groups;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = CompanyGroupType.ITEM_TYPE)
public class CompanyGroupType extends GroupType {
    public static final String ITEM_TYPE = "companyGroup";

}
