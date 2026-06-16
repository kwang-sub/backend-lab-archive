package com.example.java8.chap02;

import java.math.BigDecimal;

public class Function3 {
    public static void main(String[] args) {
        println("Area is ", 12, 20, (message, length, width) -> message + (length * width));
        println(1L, "kwang", "test@email.com", (id, name, email) -> "\nUser info\n" +
                "Id = " + id + "\nname = " + name + "\nemail = " + email);

        BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();
        System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("120.00")));

        InvalidFunctionalInterface invalidFunctionalInterface = new InvalidFunctionalInterface() {
            @Override
            public <T> String mkString(T value) {
                return value.toString();
            }
        };

        System.out.println(invalidFunctionalInterface.mkString(123));


    }

    public static  <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3, SelfFunctionalInterface<T1, T2, T3, String> function) {
        System.out.println(function.apply(t1, t2, t3));
    }
}

@FunctionalInterface
interface SelfFunctionalInterface<T1, T2, T3, R>{
     R apply(T1 t1, T2 t2, T3 t3);
}

@FunctionalInterface
interface BigDecimalToCurrency {
    String toCurrency(BigDecimal value);
}

@FunctionalInterface
interface InvalidFunctionalInterface {
    <T>String mkString(T value);
}