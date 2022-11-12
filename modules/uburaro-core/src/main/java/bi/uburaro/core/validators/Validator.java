package bi.uburaro.core.validators;

import java.util.Map;

public interface Validator<T> {
    /**
     * Validated an Item
     *
     * @param t the item to validate
     * @param errors
     */
    void validate(T t, Map<String,String> errors);

    /**
     *
     * @param tClass class of the item to validate
     * @return true if the validator support the class
     */
    boolean isSupported(Class<?> tClass);
}
