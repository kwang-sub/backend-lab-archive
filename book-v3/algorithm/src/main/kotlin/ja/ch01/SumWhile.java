package ja.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumWhile {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int sum = 0;
        int i = 1;
        while (i <= n) {
            sum += i;
            System.out.println(i);
        }
        System.out.println(sum);
    }
}
