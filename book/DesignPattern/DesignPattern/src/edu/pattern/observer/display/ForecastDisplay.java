package edu.pattern.observer.display;

import edu.pattern.observer.observer.Observer;
import edu.pattern.observer.subject.WeatherData;

public class ForecastDisplay implements DisplayElement, Observer {

    private float currentPressure = 29.92f;
    private float lastPressure;
    private WeatherData weatherData;

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("last" + lastPressure);
        System.out.println("current" + currentPressure);
    }

    @Override
    public void update() {
        this.lastPressure = currentPressure;
        this.currentPressure = weatherData.getPressure();
        display();
    }
}
