package study.java.stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class IteratorVsStreamExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3");

//        Iterator 이용
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println();
//      Stream 이용방법
        Stream<String> stream = list.stream();
        stream.forEach( name -> System.out.println(name));
    }
}
