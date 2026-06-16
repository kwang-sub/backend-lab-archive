package edu.pattern.factorymethod.pizza.store;

import edu.pattern.factorymethod.pizza.ingredient.PizzaIngredientFactory;
import edu.pattern.factorymethod.pizza.ingredient.ny.NyPizzaIngredientFactory;
import edu.pattern.factorymethod.pizza.pizza.*;
import edu.pattern.factorymethod.pizza.pizza.ny.NYStyleCheesePizza;
import edu.pattern.factorymethod.pizza.pizza.ny.NYStyleClamPizza;
import edu.pattern.factorymethod.pizza.pizza.ny.NYStylePepperoniPizz;
import edu.pattern.factorymethod.pizza.pizza.ny.NYStyleVeggiePizza;

public class NYPizzaStore extends PizzaStore{

    @Override
    protected Pizza createPizza(String item) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NyPizzaIngredientFactory();
        if (item.equals("cheese")) {
            pizza = new NYStyleCheesePizza(ingredientFactory);
            pizza.setName("뉴욕치즈피자");
            return pizza;
        } else if (item.equals("veggie")) {
            return new NYStyleVeggiePizza();
        } else if (item.equals("clam")) {
            return new NYStyleClamPizza();
        } else if (item.equals("pepperoni")) {
            return new NYStylePepperoniPizz();
        } else return null;
    }
}
