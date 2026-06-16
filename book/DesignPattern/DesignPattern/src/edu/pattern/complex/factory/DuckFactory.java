package edu.pattern.complex.factory;

import edu.pattern.complex.Quackable;
import edu.pattern.complex.duck.DuckCall;
import edu.pattern.complex.duck.MallardDuck;
import edu.pattern.complex.duck.RedheadDuck;
import edu.pattern.complex.duck.RubberDuck;

public class DuckFactory extends AbstractDuckFactory{

    @Override
    public Quackable createMallardDuck() {
        return new MallardDuck();
    }

    @Override
    public Quackable createRedheadDuck() {
        return new RedheadDuck();
    }

    @Override
    public Quackable createDuckCall() {
        return new DuckCall();
    }

    @Override
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
