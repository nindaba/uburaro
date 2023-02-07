package bi.manager.facade.data;


import bi.uburaro.facade.data.ItemData;

import java.util.HashSet;
import java.util.Set;

public class MBCategoryData extends ItemData {
    private String name;
    private String alias;
    private String address;
    private Set<MBCategoryData> categories = new HashSet<>();
    private Set<MBClientData> clients = new HashSet<>();
    private MBCapitalData capital;
}
