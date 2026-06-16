package edu.pattern.factorymethod.pizza.pizza.ch;

import edu.pattern.factorymethod.pizza.pizza.Pizza;

public class ChStyleCheesePizza extends Pizza {
    @Override
    public void prepare() {

    }

    @Override
    public void cut() {
        System.out.println("네모난 모양으로 피자 자르기");
    }
}
