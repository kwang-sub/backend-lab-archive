package edu.pattern.singleton;

public class Main {
    public static void main(String[] args) {
        ChocolateBoiler instant = ChocolateBoiler.getInstant();
        instant.isEmpty();

        ChocolateEnum e = ChocolateEnum.INSTANCE;
        ChocolateEnum e2 = ChocolateEnum.INSTANCE;

        System.out.println(e);
        System.out.println(e == e2);
    }
}
