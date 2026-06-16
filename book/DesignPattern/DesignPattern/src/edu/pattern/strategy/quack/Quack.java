package edu.pattern.strategy.quack;

public class Quack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("진짜 오리소리 : 꽥꽦!");
    }
}
