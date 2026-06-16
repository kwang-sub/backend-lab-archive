package edu.pattern.strategy.quack;

public class Squeak implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("고무오리 소리 : 삑삑");
    }
}
