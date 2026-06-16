package study.java.collection.searchcollection;

import java.util.Iterator;
import java.util.TreeSet;

public class ComparatorExample {
    public static void main(String[] args) {
        TreeSet<Fruit> treeSet = new TreeSet<Fruit>(new DescendingComparator());

        treeSet.add(new Fruit("a", 1));
        treeSet.add(new Fruit("b", 5));
        treeSet.add(new Fruit("c", 2));

        Iterator<Fruit> iterator = treeSet.iterator();
        while (iterator.hasNext()){
            Fruit f = iterator.next();
            System.out.println(f.name + " " +f.price);
        }
    }
}
