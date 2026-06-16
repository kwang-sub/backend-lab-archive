package org.example.market.exception;

import lombok.Getter;

@Getter
public class MessageArgumentException extends RuntimeException {
    private final Object[] args;

    public MessageArgumentException(Object... args) {
        this.args = args;
    }
}
