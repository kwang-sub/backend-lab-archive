package edu.pattern.adapter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        arr.add("tt");
        arr.add("bb");
        arr.add("ee");
        Enumeration test = new IteratorEnumeration(arr.iterator());

        while (test.hasMoreElements()) {
            System.out.println(test.nextElement());
        }
    }
}
