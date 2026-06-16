package study.java.collection.list.linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new LinkedList<>();

        long startTime;
        long endTime;

        startTime = System.nanoTime();
        for(int i = 0; i<10000; i++){
            list1.add(0,"test");
        }
        endTime = System.nanoTime();

        System.out.println("ArrayList 걸린시간 : " + (endTime-startTime));

        startTime = System.nanoTime();
        for(int i = 0; i<10000; i++){
            list2.add(0,"test");
        }
        endTime = System.nanoTime();

        System.out.println("LikedList 걸린시간 : " + (endTime-startTime));





    }

}
