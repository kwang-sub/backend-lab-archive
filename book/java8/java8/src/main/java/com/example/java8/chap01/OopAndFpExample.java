package com.example.java8.chap01;

public class OopAndFpExample {
    public static void main(String[] args) {
        CalculatorService calculatorService = new CalculatorService(new Addition(), new Division());
        final int addResult  = calculatorService.calculate(11, 1);
        System.out.println(addResult);
        final int subtractionResult  = calculatorService.calculate(11, 1);
        System.out.println(subtractionResult);
        final int multiplicationResult = calculatorService.calculate(11, 1);
        System.out.println(multiplicationResult);
        final int divisionResult = calculatorService.calculate(12, 2);
        System.out.println(divisionResult);

        FpCalculatorService fpCalculatorService = new FpCalculatorService();
        System.out.println( "addition : " + fpCalculatorService.calculate((i1, i2) -> i1 + i2, 11, 2));
    }
}

interface Calculation {
    int calculate(int num1, int num2);
}

class Addition implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}

class Subtraction implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 - num2;
    }
}

class Multiplication implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 * num2;
    }
}

class Division implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 / num2;
    }
}

class CalculatorService {

    private Calculation calculation;
    private Calculation calculation2;

    public CalculatorService(Calculation calculation, Calculation calculation2) {
        this.calculation = calculation;
        this.calculation2 = calculation2;
    }

    public  int calculate(int num1, int num2) {
        if (num1 > 10 && num2 < num1) {
            return calculation.calculate(num1, num2);
        } else {
            throw new IllegalArgumentException("num1 : " + num1 + "num2 : " + num2);
        }
    }
    public  int compute(int num1, int num2) {
        if (num1 > 10 && num2 < num1) {
            return calculation2.calculate(num1, num2);
        } else {
            throw new IllegalArgumentException("num1 : " + num1 + "num2 : " + num2);
        }
    }
}

class FpCalculatorService {
    public int calculate(Calculation calculation, int num1, int num2) {
        if (num1 > 10 && num2 < num1) {
            return calculation.calculate(num1, num2);
        } else {
            throw new IllegalArgumentException("num1 : " + num1 + "num2 : " + num2);
        }
    }
}
