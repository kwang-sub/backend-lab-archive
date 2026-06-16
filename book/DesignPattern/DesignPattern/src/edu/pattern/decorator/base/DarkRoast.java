package edu.pattern.decorator.base;

import edu.pattern.decorator.Beverage;

public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "다크커피";
    }

    @Override
    public double cost() {
        return 0.99;
    }
}
