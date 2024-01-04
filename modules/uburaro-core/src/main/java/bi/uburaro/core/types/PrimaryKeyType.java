package bi.uburaro.core.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static bi.uburaro.core.UburaroCoreConstants.TABLE_PREFIX;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PrimaryKeyType implements Serializable {
    public static final String PRIMARY_KEY = "primaryKey";
    public static final String KEY = "key";
    public static final String ITEM_TYPE = "itemType";
    public static final String DATE_CREATED = "dateCreated";

    @OneToOne
    @JoinColumn(name = TABLE_PREFIX + KEY)
    private GeneratedKey key;
    @Column(columnDefinition = "varchar(255) DEFAULT 'item'")
    private String itemType;
    @Column(columnDefinition = "bigint DEFAULT 0")
    private long dateCreated;
}
