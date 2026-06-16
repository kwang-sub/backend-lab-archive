package edu.pattern.singleton;

public class Singleton {
//    volatile 키워드 1.4버전까지는 사용안됨
    private volatile static Singleton uniqueInstance;

    private Singleton(){}

    public static  Singleton getInstance() {
        if (uniqueInstance == null) {
//            동기화 블럭사용
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
