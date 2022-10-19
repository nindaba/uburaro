package bi.uburaro.facade.data;

import java.util.Date;
import java.util.List;

public class ItemData {
    private String code;
    private boolean active;
    private boolean visible;
    private Date dateCreated;
    private List<ModificationLogData> modificationLogData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<ModificationLogData> getModificationLogData() {
        return modificationLogData;
    }

    public void setModificationLogData(List<ModificationLogData> modificationLogData) {
        this.modificationLogData = modificationLogData;
    }
}
