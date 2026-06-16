package org.example.market.exception;

public class AlreadyFieldException extends MessageArgumentException {

    public AlreadyFieldException(String fieldName) {
        super(fieldName);
    }
}

