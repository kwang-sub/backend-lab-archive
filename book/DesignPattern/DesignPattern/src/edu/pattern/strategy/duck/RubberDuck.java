package edu.pattern.strategy.duck;

import edu.pattern.strategy.fly.FlyBehavior;
import edu.pattern.strategy.quack.QuackBehavior;

public class RubberDuck extends Duck{

    public RubberDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(flyBehavior, quackBehavior);
    }

    @Override
    public void display() {
        System.out.println("RubberDuck 생김새");
    }
}
