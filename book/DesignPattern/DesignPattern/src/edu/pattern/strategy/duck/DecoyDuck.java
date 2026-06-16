package edu.pattern.strategy.duck;

import edu.pattern.strategy.fly.FlyBehavior;
import edu.pattern.strategy.quack.QuackBehavior;

public class DecoyDuck extends Duck{

    public DecoyDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(flyBehavior, quackBehavior);
    }

    @Override
    public void display() {
        System.out.println("DecoyDuck 생김새");
    }
}
