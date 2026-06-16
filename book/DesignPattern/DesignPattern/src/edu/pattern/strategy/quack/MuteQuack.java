package edu.pattern.strategy.quack;

public class MuteQuack implements QuackBehavior{

    @Override
    public void quack() {
        System.out.println("소리 못내는 가짜 오리");
    }
}
