package bi.uburaro.core.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = LanguageValueType.ITEM_TYPE)
public class LanguageValueType extends ItemType {
    public static final String ITEM_TYPE = "languageValue";
    public static final String LANGUAGE = "language";
    public static final String VALUE = "value";

    @OneToOne
    private LanguageType language;
    private String value;
}
