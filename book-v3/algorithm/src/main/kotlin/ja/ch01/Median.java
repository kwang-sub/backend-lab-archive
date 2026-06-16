package ja.ch01;

public class Median {

    public int med3(int a, int b, int c) {
        if (a >= b) {
            if (a <= c) return a;
            else if (b >= c) return b;
            else return c;
        } else if (a > c) {
            return a;
        } else if (b > c) return c;
        else return b;
    }


}
