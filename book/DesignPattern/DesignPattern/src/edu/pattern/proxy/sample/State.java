package edu.pattern.proxy.sample;

public interface State {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();

    default void refill(int num) {
    }


}
