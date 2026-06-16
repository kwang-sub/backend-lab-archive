package main;

import chap07.ExeTimeCalculator;
import chap07.ImplCalculator;
import chap07.RecCalculator;

public class MainProxy {

    public static void main(String[] args) {
        ExeTimeCalculator ttcal1 = new ExeTimeCalculator(new ImplCalculator());
        System.out.println(ttcal1.factorial(20));

        ExeTimeCalculator ttcal2 = new ExeTimeCalculator(new RecCalculator());
        System.out.println(ttcal2.factorial(20));
    }

}
