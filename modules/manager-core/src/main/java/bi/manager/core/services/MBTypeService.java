package bi.manager.core.services;

import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;

import java.util.Objects;
import java.util.Set;

public interface MBTypeService<ITEM extends ItemType> {

    /**
     * Finds the item, and if it is not found a Null object will be returned
     *
     * @param code
     * @return
     */
     ITEM getItemByCodeWithTry(final String code);


    /**
     * Deletes the Items related with teh codes simply by inactivating them
     *
     * @param codes
     */
    public void deleteMBItem(Set<String> codes) ;
}
