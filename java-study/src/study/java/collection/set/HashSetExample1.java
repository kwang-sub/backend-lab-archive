package study.java.collection.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetExample1 {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");

        int size = set.size();
        System.out.println("총 객체수 : " + size);
        System.out.println();

        Iterator<String> iterator = set.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println();


        set.remove("2");
        set.remove("3");

        iterator = set.iterator();
        
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        
        set.clear();
        if(set.isEmpty()) System.out.println("비어있음");

    }
}
