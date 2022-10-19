package bi.uburaro.core.exceptions;

import java.util.function.Supplier;

public class ItemCreationException extends RuntimeException{

    public ItemCreationException(ReflectiveOperationException e) {
        super(e);
    }

    public ItemCreationException(ReflectiveOperationException e, Supplier<String> responseSupplier){
        super(responseSupplier.get(),e);
    }

    public ItemCreationException(ReflectiveOperationException e, String response) {
        super(response,e);
    }
}
