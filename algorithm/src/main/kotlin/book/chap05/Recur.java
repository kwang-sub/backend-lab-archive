package book.chap05;

import book.chap04.IntStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Recur {
    static void recur(int n) {
        IntStack s = new IntStack(n);

        while (true) {
            if (n > 0) {
                s.push(n);
                n = n - 1;
                continue;
            }
            if (!s.isEmpty()) {
                n = s.pop();
                System.out.println(n);
                n = n - 2;
                continue;
            }
            break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("정수를 입력하세요");
        int x = Integer.parseInt(br.readLine());
        recur(x);
    }
}
