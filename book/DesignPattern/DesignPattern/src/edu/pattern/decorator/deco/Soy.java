package edu.pattern.decorator.deco;

import edu.pattern.decorator.Beverage;
import edu.pattern.decorator.CondimentDecorator;

public class Soy extends CondimentDecorator {
    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        double sizeCost = 0;
        if (beverage.getSize().equals(Size.TALL)){
            sizeCost = 0.1;
        } else if (beverage.getSize().equals(Size.GRANDE)) {
            sizeCost = 0.15;
        } else if (beverage.getSize().equals(Size.VENTI)) {
            sizeCost = 0.2;
        }
        return beverage.cost() + 0.15 + sizeCost;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 두유";
    }
}
