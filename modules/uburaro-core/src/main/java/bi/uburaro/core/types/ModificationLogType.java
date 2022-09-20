package bi.uburaro.core.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Date;
import java.util.List;
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class ModificationLogType extends ItemType{
    public static final String ITEM_TYPE = "modificationLog";
    public static final String USER = "user";
    private PrincipalType user;
    private Date dateModified;


    /**
     * @return empty list of modifications because the logs are never modified
     */
    public List<ModificationLogType> getModificationLogs() {
        return Collections.emptyList();
    }

    /**
     * the modification logs are not writable for the logs
     * @param modificationLogs
     */
    public void setModificationLogs(List<ModificationLogType> modificationLogs) {
        throw new UnsupportedOperationException("the modification logs are not writable for the logs");
    }
}
