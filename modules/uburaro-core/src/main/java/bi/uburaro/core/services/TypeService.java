package bi.uburaro.core.services;

import bi.uburaro.core.types.ItemType;

import java.util.List;

public interface TypeService{

    /**
     * Creates an Item and sets the primary key , the date of creation, and the type of the Item
     *
     * @param typeClass
     * @return <TYPE>
     */
     <TYPE extends ItemType> TYPE create(final Class<TYPE> typeClass);

    /**
     * Saves an Item
     *
     * @param item
     * @return
     */
     <TYPE extends ItemType> boolean save(final TYPE item);

    /**
     * Searches for an item by its code and type
     *
     * @param code of the Item
     * @param itemTypeClass of the Item
     * @return item of <TYPE>
     */
     <TYPE extends ItemType> TYPE findItemByCode(String code, Class<TYPE> itemTypeClass);

    /**
     * Searches for all the Items of typeClass
     *
     * @param typeClass
     * @return List of Items
     */
    <TYPE extends ItemType> List<TYPE> findAll(Class<TYPE> typeClass);

    /**
     * Deletes an Item by code and typeClass
     *
     * @param code
     * @param typeClass
     * @implNote  Not tested
     */
    <TYPE extends ItemType> void delete(String code,Class<TYPE> typeClass);

    /**
     * Deletes an Item
     *
     * @param item
     * @implNote  Not tested
     */
    <TYPE extends ItemType> void delete(TYPE item);
}
