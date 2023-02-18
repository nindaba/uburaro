package bi.uburaro.core.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException{

    Logger LOG = LogManager.getLogger();

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
        LOG.error(response);
    }

}
