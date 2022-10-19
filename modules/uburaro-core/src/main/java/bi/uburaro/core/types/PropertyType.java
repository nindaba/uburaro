package bi.uburaro.core.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Index;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@MappedSuperclass
@Table(indexes = @Index(name = PropertyType.KEY + "_index", columnList = PropertyType.KEY))
public class PropertyType extends ItemType {
    public static final String ITEM_TYPE = "property";
    public static final String KEY = "key";
    public static final String VALUE = "value";
    @Column(name = ITEM_TYPE + "_" + KEY)
    private String key;
}
