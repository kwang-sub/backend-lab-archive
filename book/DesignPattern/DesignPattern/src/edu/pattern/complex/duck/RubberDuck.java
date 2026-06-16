package edu.pattern.complex.duck;

import edu.pattern.complex.Quackable;
import edu.pattern.complex.observer.Observable;
import edu.pattern.complex.observer.Observer;

public class RubberDuck implements Quackable {

    Observable observable;

    public RubberDuck() {
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("삑삑");
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }

    @Override
    public String toString() {
        return "고무오리";
    }
}
