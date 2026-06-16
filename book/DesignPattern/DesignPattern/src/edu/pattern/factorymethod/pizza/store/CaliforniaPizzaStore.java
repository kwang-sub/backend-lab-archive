package edu.pattern.factorymethod.pizza.store;

import edu.pattern.factorymethod.pizza.pizza.*;
import edu.pattern.factorymethod.pizza.pizza.ca.CaStyleCheesePizza;
import edu.pattern.factorymethod.pizza.pizza.ca.CaStyleClamPizza;
import edu.pattern.factorymethod.pizza.pizza.ca.CaStylePepperoniPizz;
import edu.pattern.factorymethod.pizza.pizza.ca.CaStyleVeggiePizza;

public class CaliforniaPizzaStore extends PizzaStore{
    @Override
    protected Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new CaStyleCheesePizza();
        } else if (item.equals("veggie")) {
            return new CaStyleVeggiePizza();
        } else if (item.equals("clam")) {
            return new CaStyleClamPizza();
        } else if (item.equals("pepperoni")) {
            return new CaStylePepperoniPizz();
        } else return null;
    }
}
