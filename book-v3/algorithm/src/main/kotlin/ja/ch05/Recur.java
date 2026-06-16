package ja.ch05;

import ja.ch04.IntStack;

public class Recur {
    static void recur(int n) {
        if (n > 0) {
            recur(n - 1);
            System.out.println(n);
            recur(n - 2);
        }
    }

    static void recur2(int n) {
        while (n > 0) {
            recur2(n - 1);
            System.out.println(n);
            n = n - 2;
        }
    }

    static void recur3(int n) {
        IntStack s = new IntStack(n);

        while (true) {
            if (n > 0) {
                s.push(n);
                n = n - 1;
                continue;
            }

            if (s.isEmpty() != true) {
                n = s.pop();
                System.out.println(n);
                n = n - 2;
                continue;
            }
            break;
        }
    }

    public static void main(String[] args) {
        recur(3);

        System.out.println("-----");
        recur2(3);

        System.out.println("-----");
        recur3(3);
    }
}
