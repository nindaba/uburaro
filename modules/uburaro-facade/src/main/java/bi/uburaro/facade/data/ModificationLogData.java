package bi.uburaro.facade.data;

import java.util.Date;

public class ModificationLogData extends ItemData{
    private PrincipalData user;
    private String modifiedItem;
    private Date dateModified;
    private String previousValueCode;

    public PrincipalData getUser() {
        return user;
    }

    public void setUser(PrincipalData user) {
        this.user = user;
    }

    public String getModifiedItem() {
        return modifiedItem;
    }

    public void setModifiedItem(String modifiedItem) {
        this.modifiedItem = modifiedItem;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getPreviousValueCode() {
        return previousValueCode;
    }

    public void setPreviousValueCode(String previousValueCode) {
        this.previousValueCode = previousValueCode;
    }
}
