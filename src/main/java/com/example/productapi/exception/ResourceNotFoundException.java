package com.example.productapi.exception;

/**
 * @author FullStack Labs
 * @version 1.0
 * @since 2021-10-22
 */

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
