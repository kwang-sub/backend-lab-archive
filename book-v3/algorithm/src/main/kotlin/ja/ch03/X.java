package ja.ch03;

import java.util.Comparator;

public class X<T> {
    public final Comparator<T> COMPARATOR = new Comp();

    private static class Comp<T> implements Comparator<T> {
        public int compare(T d1, T d2) {
            return -1;
        }
    }
}
