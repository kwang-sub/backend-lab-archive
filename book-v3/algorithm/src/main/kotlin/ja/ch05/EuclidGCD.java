package ja.ch05;

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
        System.out.println("두 정수의 최대공약수를 구합니다.");

        System.out.print("정수 x: ");
        int x = Integer.parseInt(br.readLine());
        System.out.print("정수 y: ");
        int y = Integer.parseInt(br.readLine());

        System.out.println(x + "와 " + y + "의 최대공약수는 " + gcd(x, y) + "입니다.");
    }
}
