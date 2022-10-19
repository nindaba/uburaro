package bi.uburaro.web.controllers;

import bi.uburaro.core.exceptions.ItemCreationException;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.facade.data.ErrorData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorData> notFoundErrors(final Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorData("Not Found Error", e.getMessage()));
    }

    @ExceptionHandler({ItemCreationException.class})
    public ResponseEntity<ErrorData> creationErrors(final Exception e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorData("ItemCreation Errors", e.getMessage()));
    }
}
