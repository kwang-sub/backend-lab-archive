package study.java.collection.searchcollection;

public class PerSon implements Comparable<PerSon>{
    public String name;
    public int age;

    public PerSon(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(PerSon o) {
        if(age<o.age) return -1;
        else if (age == o.age) return 0;
        else return 1;

    }
}
