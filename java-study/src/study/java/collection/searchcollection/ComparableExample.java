package study.java.collection.searchcollection;

import java.util.Iterator;
import java.util.TreeSet;

public class ComparableExample {
    public static void main(String[] args) {
        TreeSet<PerSon> treeSet = new TreeSet<>();

        treeSet.add(new PerSon("kwang1", 6));
        treeSet.add(new PerSon("kwang2", 2));
        treeSet.add(new PerSon("kwang3", 3));

        Iterator<PerSon> iterator = treeSet.iterator();
        while (iterator.hasNext()){
            PerSon person = iterator.next();
            System.out.println(person.name + " " + person.age);
        }

    }
}
