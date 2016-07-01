package com.globmart.service.exception;
/**
 * Created by subramanian_p on 30-06-2016.
 */
public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(final String message) {
        super(message);
    }
}
