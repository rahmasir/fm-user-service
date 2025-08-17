package org.rahmasir.fmuserservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Global exception handler to catch custom exceptions and return
 * standardized error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomExceptions.ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(CustomExceptions.ResourceNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomExceptions.ForbiddenAccessException.class)
    public ResponseEntity<Object> handleForbiddenAccessException(CustomExceptions.ForbiddenAccessException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CustomExceptions.EmailAlreadyExistsException.class)
    public ResponseEntity<Object> handleEmailAlreadyExistsException(CustomExceptions.EmailAlreadyExistsException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.CONFLICT);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception ex, HttpStatus status) {
        Map<String, Object> body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", ex.getMessage()
        );
        return new ResponseEntity<>(body, status);
    }
}
