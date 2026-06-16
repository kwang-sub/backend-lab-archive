package edu.pattern.observer.subject;

import edu.pattern.observer.observer.Observer;

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
