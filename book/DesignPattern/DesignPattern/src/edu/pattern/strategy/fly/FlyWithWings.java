package edu.pattern.strategy.fly;

public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("날 수 있어요!");
    }
}
