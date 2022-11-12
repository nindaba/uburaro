package bi.uburaro.core.exceptions;

import java.util.function.Supplier;

public class ValidationException extends RuntimeException{
    public ValidationException(Throwable e) {
        super(e);
    }

    public ValidationException(Throwable e, Supplier<String> responseSupplier){
        super(responseSupplier.get(),e);
    }
    public ValidationException(Supplier<String> responseSupplier){
        super(responseSupplier.get());
    }
    public ValidationException(Throwable e, String response) {
        super(response,e);
    }
    public ValidationException(String response) {
        super(response);
    }
}
