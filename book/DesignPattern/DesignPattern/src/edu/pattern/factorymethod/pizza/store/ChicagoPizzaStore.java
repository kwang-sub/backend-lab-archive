package edu.pattern.factorymethod.pizza.store;

import edu.pattern.factorymethod.pizza.pizza.*;
import edu.pattern.factorymethod.pizza.pizza.ch.ChStyleCheesePizza;
import edu.pattern.factorymethod.pizza.pizza.ch.ChStyleClamPizza;
import edu.pattern.factorymethod.pizza.pizza.ch.ChStylePepperoniPizz;
import edu.pattern.factorymethod.pizza.pizza.ch.ChStyleVeggiePizza;

public class ChicagoPizzaStore extends PizzaStore{
    @Override
    protected Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new ChStyleCheesePizza();
        } else if (item.equals("veggie")) {
            return new ChStyleVeggiePizza();
        } else if (item.equals("clam")) {
            return new ChStyleClamPizza();
        } else if (item.equals("pepperoni")) {
            return new ChStylePepperoniPizz();
        } else return null;
    }
}
