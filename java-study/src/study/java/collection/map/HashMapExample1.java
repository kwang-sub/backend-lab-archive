package study.java.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapExample1 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        map.put("4",4);

        System.out.println("총 Entry 수 : " + map.size());
        System.out.println();

        System.out.println("1 " + map.get("1"));
        System.out.println();

        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            int value = map.get(next);
            System.out.println(next + "\t" + value);
        }

        map.remove("1");
        System.out.println("삭제후 수" + map.size());

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<String,Integer>> iterator1 = entrySet.iterator();
        while (iterator1.hasNext()){
            Map.Entry<String, Integer> entry = iterator1.next();
            String key = entry.getKey();
            int value = entry.getValue();
            System.out.println(key + " " + value);
        }
    }
}
