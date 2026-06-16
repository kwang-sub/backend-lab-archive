package edu.pattern.templatemethod;

public class Tea extends CaffeineBeverage {

    @Override
    public void brew() {
        System.out.println("찻잎을 우려내는중");
    }

    @Override
    public void addCondiments() {
        System.out.println("레몬을 추가하는 중");
    }
}
