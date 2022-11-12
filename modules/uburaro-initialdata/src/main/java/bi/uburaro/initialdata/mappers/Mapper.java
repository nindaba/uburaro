package bi.uburaro.initialdata.mappers;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.initialdata.data.BatchLineData;

import java.util.Map;
import java.util.function.Consumer;

public interface Mapper<TYPE extends ItemType> {

    /**
     * Gets the target class of the mapper
     * @return class of target
     */
    Class<TYPE> getTargetClass();

    /**
     * Sets a value to a field of target
     *
     * @param field  to set the value
     * @param value  to set
     * @param target of the field to be set
     */
    void set(BatchLineData field, BatchLineData value, TYPE target);

    /**
     * Sets a values to a fields of target
     *
     * @param fields       to set as ',' separated
     * @param targetValues each entry represents a type
     *                     and its values ',' separated in order of the <code>fields</code>
     */
    void set(BatchLineData fields, Map<TYPE, BatchLineData> targetValues);

    /**
     * Gets a map of fields and setters to the target
     *
     * @param target
     * @return map of fields names and the setters of the fields
     */
    Map<String, Consumer<String>> createFieldsMapper(TYPE target);
}
