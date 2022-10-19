package bi.uburaro.core.exceptions;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException{

    public NotFoundException(Throwable e) {
        super(e);
    }

    public NotFoundException(Throwable e, Supplier<String> responseSupplier){
        super(responseSupplier.get(),e);
    }

    public NotFoundException(Throwable e, String response) {
        super(response,e);
    }
    public NotFoundException(String response) {
        super(response);
    }

}
