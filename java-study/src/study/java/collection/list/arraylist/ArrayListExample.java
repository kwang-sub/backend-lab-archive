package study.java.collection.list.arraylist;

import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("Java");
        list.add("test");
        list.add("2");
        list.add("3");

        int size = list.size();
        System.out.println("총 객체수 : " + size);
        System.out.println();

        String two = list.get(2);
        System.out.println("2 : " + two);
        System.out.println();

        for(String s : list) {
            System.out.println(s);
        }
        System.out.println();

        list.remove(2);
        list.remove(2);
        for(String s : list){
            System.out.println(s);
        }


    }
}
