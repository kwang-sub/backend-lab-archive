package study.java.collection.set;

import java.util.HashSet;
import java.util.Set;

public class HashSetExample2 {
    public static void main(String[] args) {

        Set<Member> set = new HashSet<>();

        set.add(new Member("test", 2));
        set.add(new Member("test", 2));

        System.out.println(set.size());

    }
}
