package study.java.collection.list.arraylist;

import java.util.Arrays;
import java.util.List;

public class ArraysAsListExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3");
        for(String s : list){
            System.out.println(s);
        }
    }
}
