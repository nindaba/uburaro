package bi.uburaro.core.validators;

import java.util.Map;

public interface Validator<T> {
    void validate(T t, Map<String,String> errors);
    boolean isSupported(Class<?> tClass);
}
