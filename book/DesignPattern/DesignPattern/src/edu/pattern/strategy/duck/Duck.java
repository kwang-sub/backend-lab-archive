package edu.pattern.strategy.duck;

import edu.pattern.strategy.fly.FlyBehavior;
import edu.pattern.strategy.quack.QuackBehavior;

public abstract class Duck {

    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
    }


    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }


    public void swim(){
        System.out.println("수영하기");
    };

    public abstract void display();

    public void performQuack(){
        quackBehavior.quack();
    };

    public void performFly(){
        flyBehavior.fly();
    };
}

