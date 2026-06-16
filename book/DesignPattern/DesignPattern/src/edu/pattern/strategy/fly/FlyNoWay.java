package edu.pattern.strategy.fly;

public class FlyNoWay implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("날지 못합니다!");
    }
}
