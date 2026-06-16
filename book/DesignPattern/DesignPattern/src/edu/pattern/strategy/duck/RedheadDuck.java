package edu.pattern.strategy.duck;

import edu.pattern.strategy.fly.FlyBehavior;
import edu.pattern.strategy.quack.QuackBehavior;

public class RedheadDuck extends Duck{

    public RedheadDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(flyBehavior, quackBehavior);
    }

    @Override
    public void display() {
        System.out.println("RedheadDuck 생김새");
    }
}
