package study.java.collection.searchcollection;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample1 {
    public static void main(String[] args) {
        TreeMap<Integer, String> scores = new TreeMap<>();
        scores.put(1, "1");
        scores.put(2, "2");
        scores.put(3, "3");
        scores.put(4, "4");

        Map.Entry<Integer, String> entry = null;

        entry = scores.firstEntry();
        System.out.println("가장 낮은 점수 : " + entry.getKey() + "-" + entry.getValue());

        entry = scores.lastEntry();
        System.out.println("가장 높은 점수 : " + entry.getKey() + "-" + entry.getValue());

        entry = scores.lowerEntry(3);
        System.out.println("3아래 점수 : " + entry.getKey() + "-" + entry.getValue());

        while (!scores.isEmpty()){
            entry = scores.pollFirstEntry();
            System.out.println(entry.getKey() +"-"+ entry.getValue());
            System.out.println(scores.size());
        }



    }
}
