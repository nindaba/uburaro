package bi.uburaro.core.types.groups;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = TaxGroupType.ITEM_TYPE)
public class TaxGroupType extends GroupType {
    public static final String ITEM_TYPE = "taxGroup";
    public static final String TAX_VALUE = "taxValue";
    public static final String TAX_PRIORITY = "taxPriority";

    private Double taxValue;
    private Integer taxPriority;
}
