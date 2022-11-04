package com.tizo.productapi.exception;

/**
 * @author FullStack Labs
 * @version 1.0
 * @since 2021-10-22
 */
public class JWTForbiddenException extends RuntimeException{

    public JWTForbiddenException(final String message) {
        super(message);
    }
}
