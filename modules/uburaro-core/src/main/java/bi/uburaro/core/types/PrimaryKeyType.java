package bi.uburaro.core.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static bi.uburaro.core.UburaroCoreConstants.COLUMN_PREFIX;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PrimaryKeyType implements Serializable {
    public static final String PRIMARY_KEY = "primaryKey";
    public static final String KEY = "key";
    public static final String ITEM_TYPE = "itemType";

    @GeneratedValue
    @Column(name = COLUMN_PREFIX  + KEY)
    private long key;
    private String itemType;
    private long dateCreated;

}
