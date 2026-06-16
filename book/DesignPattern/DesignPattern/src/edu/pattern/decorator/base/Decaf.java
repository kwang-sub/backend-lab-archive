package edu.pattern.decorator.base;

import edu.pattern.decorator.Beverage;

public class Decaf extends Beverage {
    public Decaf() {
        description = "디카페인";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
