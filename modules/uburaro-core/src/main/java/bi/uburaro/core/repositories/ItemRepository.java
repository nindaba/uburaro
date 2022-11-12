package bi.uburaro.core.repositories;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.PrimaryKeyType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


public interface ItemRepository<TYPE extends ItemType> extends JpaRepository<TYPE, PrimaryKeyType> {

    /**
     * Searches for an Item by code and its type
     *
     * @param code of the item
     * @param type of the item
     * @return Item
     */
    TYPE findByCodeAndPrimaryKeyItemType(String code,String type);

    /**
     * Deletes an Item by code and type
     *
     * @param code of the item
     * @param type of the item
     */
    void deleteByCodeAndPrimaryKeyItemType(String code,String type);

    /**
     * Checks if a repository belongs to a type,
     *
     * @param typeClass
     * @return false by default because it belongs to all the Types
     */
    default <TYPE extends ItemType> boolean belongsTo(Class<TYPE> typeClass){
        return false;
    }
}
