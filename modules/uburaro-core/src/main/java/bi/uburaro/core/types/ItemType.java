package bi.uburaro.core.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = ItemType.ITEM_TYPE)
@Table( indexes = {
        @Index(name = ItemType.CODE+"_index", columnList = ItemType.CODE),
        @Index(name = ItemType.DATE_CREATED+"_index", columnList = ItemType.DATE_CREATED)
})
@EqualsAndHashCode(exclude = {"modificationLogs"})
@Inheritance(strategy = InheritanceType.JOINED)
public class ItemType implements Serializable {

    public static final String ITEM_TYPE = "item";
    public static final String CODE = "code";
    public static final String PRIMARY_KEY = "primaryKey";
    public static final String DATE_CREATED = "dateCreated";
    public static final String DATE_MODIFIED= "dateModified";
    public static final String MODIFICATION_LOGS= "modificationLogs";
    public static final String VISIBLE = "visible";
    public static final String ACTIVE = "active";
    @Id
    private PrimaryKeyType primaryKey;
    private String code;
    private Date dateModified;
    /*
    The Item may longer be wanted to be available but, it has been used in many places, this means that it should nolonger be visible in the future
     */
    private boolean visible;
    /*

    The Item many be suspended for a while
     */
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<ModificationLogType> modificationLogs = new HashSet<>();

    public ItemType(String code) {
        this.code = code;
    }
}
