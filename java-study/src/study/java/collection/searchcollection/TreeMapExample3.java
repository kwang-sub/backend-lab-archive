package study.java.collection.searchcollection;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TreeMapExample3 {
    public static void main(String[] args) {
        TreeMap<String,Integer> treeMap = new TreeMap<>();
        treeMap.put("appel",1);
        treeMap.put("forever",2);
        treeMap.put("description",3);
        treeMap.put("zoo",4);
        treeMap.put("kwang",5);

        System.out.println("[c~f 사이의 단어 검색]");
        NavigableMap<String,Integer> rangeMap = treeMap.subMap("c",true, "f", true);
        for (Map.Entry<String, Integer> entry : rangeMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
