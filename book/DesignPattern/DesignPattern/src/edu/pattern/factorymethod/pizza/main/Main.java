package edu.pattern.factorymethod.pizza.main;

import edu.pattern.factorymethod.pizza.pizza.Pizza;
import edu.pattern.factorymethod.pizza.store.NYPizzaStore;
import edu.pattern.factorymethod.pizza.store.PizzaStore;

public class Main {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        System.out.println("치즈피자 주문이요~");
//        피자를 주문!
        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("피자명 : " + pizza.getName());
    }
}
