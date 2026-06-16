package study.java.collection.searchcollection;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetExample2 {
    public static void main(String[] args) {
        TreeSet<Integer> scores = new TreeSet<>();
        scores.add(87);
        scores.add(81);
        scores.add(85);
        scores.add(22);
        scores.add(83);

        NavigableSet<Integer> deNavigableSet = scores.descendingSet();
        for(Integer score : deNavigableSet){
            System.out.println(score);
        }
        System.out.println();

        NavigableSet<Integer> ascNavigableSet = deNavigableSet.descendingSet();
        for(Integer score : ascNavigableSet){
            System.out.println(score);
        }
    }
}
