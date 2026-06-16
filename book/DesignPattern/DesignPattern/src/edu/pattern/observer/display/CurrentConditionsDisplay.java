package edu.pattern.observer.display;

import edu.pattern.observer.observer.Observer;
import edu.pattern.observer.subject.WeatherData;

//날씨 정보를 받아오기위해 Observer를 구현하고 화면을 구현하기위해 DisplayElement를 구현했다.
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private float temperature; //기온
    private float humidity; //습도
    private WeatherData weatherData;

//  생성자를 통해 WeatherData를 주입받고 주제객체인 weatherData에 registerObserver를 이용해
//  현재 디스플레이를 옵저버로 등록함(Observer인터페이스를 구현한 이유)
    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.printf("현재 상태: 온도 %.2f도 습도 %.2f퍼센트\n ", temperature, humidity);
    }

    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        display();
    }
}
