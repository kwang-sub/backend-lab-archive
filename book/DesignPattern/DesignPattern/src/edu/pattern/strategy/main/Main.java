package edu.pattern.strategy.main;

import edu.pattern.strategy.duck.Duck;
import edu.pattern.strategy.duck.MallardDuck;
import edu.pattern.strategy.fly.FlyNoWay;
import edu.pattern.strategy.fly.FlyWithWings;
import edu.pattern.strategy.quack.Quack;

public class Main {
    public static void main(String[] args) {
        Duck duck = new MallardDuck(new FlyWithWings(), new Quack());
        duck.setFlyBehavior(new FlyNoWay());
        duck.performFly();
    }
}
