package book.chap01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumWhile {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1부터 n까지의 합을 구합니다.");
        System.out.println("n의 값 : ");
        int n = Integer.parseInt(br.readLine());

        int sum = 0;
//        int i = 1;
//        while (i <= n) {
//            sum += i;
//            System.out.println("1씩 증가 하는 중 " + i);
//            i++;
//        }
        for (int i = 1; i <= n; i++) {
            sum += i;
            System.out.println("1씩 증가 하는 중 " + i);
        }

        System.out.println("1부터 " + n + "까지의 합은 " + sum + "입니다.");
    }
}
