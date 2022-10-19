package bi.uburaro.core.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = ConfigurationType.ITEM_TYPE)
public class ConfigurationType extends PropertyType {
    public static final String ITEM_TYPE = "configuration";
    public static final String VALUE = "value";

    private String value;
}
