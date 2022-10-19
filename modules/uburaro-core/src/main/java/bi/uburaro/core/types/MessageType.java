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
@Entity(name = MessageType.ITEM_TYPE)
public class MessageType extends PropertyType{
    public static final String ITEM_TYPE = "message";
    public static final String VALUES = "values";

    @OneToMany
    private List<LanguageValueType> values;

}
