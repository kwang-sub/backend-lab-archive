package edu.pattern.complex;

import edu.pattern.complex.observer.QuackObservable;

public interface Quackable extends QuackObservable {
    public void quack();
}
