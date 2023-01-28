package bi.uburaro.core.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static bi.uburaro.core.UburaroCoreConstants.DATABASE_KEYWORDS_PREFIX;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PrimaryKeyType implements Serializable {
    public static final String PRIMARY_KEY = "primaryKey";
    public static final String KEY = "key";
    public static final String ITEM_TYPE = "itemType";
    public static final String ITEM = "item";

    @Column(name = DATABASE_KEYWORDS_PREFIX + KEY, columnDefinition = "bigint DEFAULT 0")
    private long key;
    @Column(columnDefinition = "varchar(255) DEFAULT 'item'")
    private String itemType;
    @Column(columnDefinition = "bigint DEFAULT 0")
    private long dateCreated;

}
