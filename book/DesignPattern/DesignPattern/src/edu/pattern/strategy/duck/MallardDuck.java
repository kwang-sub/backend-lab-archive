package edu.pattern.strategy.duck;

import edu.pattern.strategy.fly.FlyBehavior;
import edu.pattern.strategy.quack.QuackBehavior;

public class MallardDuck extends Duck{

    public MallardDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(flyBehavior, quackBehavior);
    }

    @Override
    public void display() {
        System.out.println("MallardDuck 생김새");
    }
}
