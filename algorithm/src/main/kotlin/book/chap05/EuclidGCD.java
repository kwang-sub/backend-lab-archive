package book.chap05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EuclidGCD {
    static int gcd(int x, int y) {
        if (y == 0) return x;
        else return gcd(y, x % y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("두 정수의 최대 공약수를 구합니다.");

        System.out.println("정수를 입력하세요");
        int x = Integer.parseInt(br.readLine());
        System.out.println("정수를 입력하세요");
        int y = Integer.parseInt(br.readLine());
        System.out.println("두 수의 최대 공약수는 " + gcd(x, y));
    }
}
