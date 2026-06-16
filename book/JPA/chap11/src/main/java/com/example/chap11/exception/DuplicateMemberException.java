package com.example.chap11.exception;

public class DuplicateMemberException extends RuntimeException{

    public DuplicateMemberException() {
        super();
    }

    public DuplicateMemberException(String message) {
        super(message);
    }
}
