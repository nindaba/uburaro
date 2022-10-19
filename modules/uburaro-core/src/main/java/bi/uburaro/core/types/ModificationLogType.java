package bi.uburaro.core.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = ModificationLogType.ITEM_TYPE)
public class ModificationLogType extends ItemType {
    public static final String ITEM_TYPE = "modificationLog";
    public static final String USER = "user";
    public static final String DATE_MODIFIED = "dateModified";
    public static final String MODIFIED_ITEM = "modifiedItem";
    public static final String PREVIOUS_VALUE = "previousValueCode";

    @ManyToOne
    private PrincipalType user;
    private String modifiedItem;
    private Date dateModified;
    private String previousValueCode;


    /**
     * @return empty list of modifications because the logs are never modified
     */
    public List<ModificationLogType> getModificationLogs() {
        return Collections.emptyList();
    }

    /**
     * the modification logs are not writable for the logs
     *
     * @param modificationLogs
     */
    public void setModificationLogs(List<ModificationLogType> modificationLogs) {
        throw new UnsupportedOperationException("the modification logs are not writable for the logs");
    }
}
