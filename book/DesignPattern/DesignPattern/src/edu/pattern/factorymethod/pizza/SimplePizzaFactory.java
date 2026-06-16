package edu.pattern.factorymethod.pizza;

import edu.pattern.factorymethod.pizza.pizza.*;
import edu.pattern.factorymethod.pizza.pizza.base.CheesePizza;
import edu.pattern.factorymethod.pizza.pizza.base.ClamPizza;
import edu.pattern.factorymethod.pizza.pizza.base.PepperoniPizza;
import edu.pattern.factorymethod.pizza.pizza.base.veggiePizza;

public class SimplePizzaFactory {

    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")){
            pizza = new CheesePizza();
        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        } else if (type.equals("clam")) {
            pizza = new ClamPizza();
        } else if (type.equals("veggie")) {
            pizza = new veggiePizza();
        }

        return pizza;
    }
}
