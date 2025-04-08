package org.socialnetwork.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialnetwork.definitions.Error;
import org.socialnetwork.definitions.IValue;
import org.socialnetwork.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<? extends IValue> handleValidationException(ValidationException ex, WebRequest request) {
        return handleException(ex, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ResponseEntity<? extends IValue> handleException(Exception ex, HttpStatus status) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(buildError(ex), status);
    }

    private Error buildError(Exception ex) {
        String exceptionName = ex.getClass().getSimpleName();
        String exceptionMessage = ex.getMessage();
        String errorMessage = String.format("%s: %s", exceptionName, exceptionMessage);

        return new Error(errorMessage);
    }
}
