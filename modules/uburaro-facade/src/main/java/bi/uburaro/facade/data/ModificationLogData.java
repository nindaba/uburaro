package bi.uburaro.facade.data;

import java.util.Date;

public class ModificationLogData extends ItemData{
    private PrincipalData user;
    private String modifiedItem;
    private Date dateModified;
    private String previousValueCode;
}
