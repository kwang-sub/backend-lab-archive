package study.java.collection.searchcollection;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapExample2 {
    public static void main(String[] args) {
        TreeMap<Integer,String> scores = new TreeMap<>();
        scores.put(1,"1");
        scores.put(2,"2");
        scores.put(3,"3");
        scores.put(4,"4");
        scores.put(5,"5");

        NavigableMap<Integer,String> descendingMap = scores.descendingMap();
        Set<Map.Entry<Integer,String>> descendingEntrySet = descendingMap.entrySet();
        for (Map.Entry<Integer,String> entry : descendingEntrySet){
            System.out.println(entry.getKey() + " " + entry.getValue());

        }
        System.out.println();
        NavigableMap<Integer,String> ascendingMap = descendingMap.descendingMap();
        Set<Map.Entry<Integer,String>> ascendingEntrySet = ascendingMap.entrySet();
        for(Map.Entry<Integer,String> entry : ascendingEntrySet){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}
