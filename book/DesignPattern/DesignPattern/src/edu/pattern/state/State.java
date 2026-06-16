package edu.pattern.state;

public interface State {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();

    default void refill(int num) {

    }
}
