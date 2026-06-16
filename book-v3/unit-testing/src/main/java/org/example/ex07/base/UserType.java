package org.example.ex07.base;

public enum UserType {
    CUSTOMER(1),
    EMPLOYEE(2);

    private final int value;

    UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
