package chap01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Max3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("세 정수의 최댓값을 구합니다.");
        System.out.println("a의 값 : ");
        int a = Integer.parseInt(br.readLine());
        System.out.println("b의 값 : ");
        int b = Integer.parseInt(br.readLine());
        System.out.println("c의 값 : ");
        int c = Integer.parseInt(br.readLine());

        int max = getMax3(a, b, c);

        System.out.println("최댓값은 " + max + "입니다.");
    }

    private static int getMax3(int a, int b, int c) {
        int max = a;
        if (b > max) max = b;
        if (c > max) max = c;
        return max;
    }
}
