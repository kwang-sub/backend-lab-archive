package edu.pattern.factorymethod.pizza.pizza.ny;

import edu.pattern.factorymethod.pizza.ingredient.PizzaIngredientFactory;
import edu.pattern.factorymethod.pizza.pizza.Pizza;

public class NYStyleCheesePizza extends Pizza {

    PizzaIngredientFactory ingredientFactory;

    public NYStyleCheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("준비 중 " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
    }
}
