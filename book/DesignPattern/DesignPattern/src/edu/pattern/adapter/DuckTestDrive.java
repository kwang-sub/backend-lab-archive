package edu.pattern.adapter;

public class DuckTestDrive {
    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(turkey);

        System.out.println("adapter");
        turkeyAdapter.quack();
        turkeyAdapter.fly();
    }
}
