package edu.pattern.templatemethod;

public abstract class CaffeineBeverage {
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
        hook();
    }

    public void hook() {

    }

    public abstract void brew();
    public abstract void addCondiments();

    public void boilWater() {
        System.out.println("물 끓이는 중입니다.");
    }

    public void pourInCup() {
        System.out.println("컵에 따르는 중입니다.");
    }
}
