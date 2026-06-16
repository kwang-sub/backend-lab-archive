package edu.pattern.templatemethod;

import java.util.Arrays;

public class DuckSortTestDrive {
    public static void main(String[] args) {
        Duck[] ducks = {
                new Duck("1", 1),
                new Duck("5", 5),
                new Duck("3", 3),
                new Duck("2", 2),
                new Duck("4", 4)
        };

        System.out.println("정렬전");
        display(ducks);

        Arrays.sort(ducks);
        System.out.println("정렬후");
        display(ducks);

    }

    static void display(Duck[] ducks) {
        for (Duck duck : ducks) {
            System.out.println(duck);
        }
    }
}
