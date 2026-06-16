package edu.pattern.complex.duck;

import edu.pattern.complex.Quackable;
import edu.pattern.complex.observer.Observable;
import edu.pattern.complex.observer.Observer;


public class MallardDuck implements Quackable {

    Observable observable;

    public MallardDuck() {
        this.observable = new Observable(this);
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
    public void quack() {
        System.out.println("꽥꽥");
        notifyObservers();
    }

    @Override
    public String toString() {
        return "물오리";
    }
}
