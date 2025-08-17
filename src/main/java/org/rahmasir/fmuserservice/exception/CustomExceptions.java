package org.rahmasir.fmuserservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A collection of custom exceptions for the application.
 */
public class CustomExceptions {

    /**
     * Thrown when a requested resource (e.g., a User or Profile) cannot be found.
     * Maps to HTTP 404 Not Found.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Thrown when a user attempts an action they are not authorized to perform.
     * Maps to HTTP 403 Forbidden.
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public static class ForbiddenAccessException extends RuntimeException {
        public ForbiddenAccessException(String message) {
            super(message);
        }
    }

    /**
     * Thrown when attempting to register with an email that already exists.
     * Maps to HTTP 409 Conflict.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String message) {
            super(message);
        }
    }
}
