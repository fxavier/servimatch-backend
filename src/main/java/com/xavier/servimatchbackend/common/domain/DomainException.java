package com.xavier.servimatchbackend.common.domain;

/**
 * Exception class for domain-specific errors.
 * This exception is used to indicate issues that arise within the domain layer of the application.
 * It extends RuntimeException, allowing it to be thrown without being declared in method signatures.
 */

public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(Throwable cause) {
        super(cause);
    }
}
