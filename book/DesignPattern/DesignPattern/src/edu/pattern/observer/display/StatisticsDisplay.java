package edu.pattern.observer.display;

import edu.pattern.observer.observer.Observer;
import edu.pattern.observer.subject.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class StatisticsDisplay implements Observer, DisplayElement {

    private List<Float> data;
    private float min;
    private float max;
    private float avg;
    private WeatherData weatherData;

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        data = new ArrayList<Float>();
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.printf("최저기온: %2f, 최고기온: %2f, 평균기온: %2f \n", min, max, avg);
    }

    @Override
    public void update() {
        data.add(weatherData.getTemperature());
        float sum = 0;
        for (float f : data) {
            if (min > f || min == 0) {
                min = f;
            }
            if (max < f){
                max = f;
            }
            sum += f;
        }
        avg = sum / data.size();
        display();
    }
}
