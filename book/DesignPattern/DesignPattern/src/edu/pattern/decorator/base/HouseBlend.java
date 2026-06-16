package edu.pattern.decorator.base;

import edu.pattern.decorator.Beverage;

public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "하우스 블렌드 커피";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
