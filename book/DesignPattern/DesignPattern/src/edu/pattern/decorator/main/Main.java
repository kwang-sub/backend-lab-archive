package edu.pattern.decorator.main;

import edu.pattern.decorator.Beverage;
import edu.pattern.decorator.base.DarkRoast;
import edu.pattern.decorator.base.Espresso;
import edu.pattern.decorator.deco.Mocha;
import edu.pattern.decorator.deco.Soy;
import edu.pattern.decorator.deco.Whip;

public class Main {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        Beverage beverage1 = new DarkRoast();
        beverage1 = new Mocha(beverage1);
        beverage1 = new Mocha(beverage1);
        beverage1 = new Whip(beverage1);
        beverage1 = new Soy(beverage1);
        System.out.println(beverage1.getDescription() + " $" + beverage1.cost());
    }
}
